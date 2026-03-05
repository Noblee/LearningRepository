# 面试题：设计一个限流组件

> 适用候选人：Go 后端开发，有高并发微服务经验（字节跳动 MCN 平台、7K+ QPS、Hertz/Kitex 技术栈）

---

## 面试官引导脚本

> 以下按阶段递进，面试官可根据候选人的回答深度灵活跳转。建议总时长 20-30 分钟。

---

## 第一阶段：开放式提问（5 分钟）

### 面试官说：

> "假设你现在需要给你们的 MCN 机构平台设计一个通用的限流组件，这个组件要能在你们的 Hertz 和 Kitex 服务中复用，支撑多种限流场景。请你从头开始设计这个组件——你会怎么思考和规划？"

**考察意图：** 观察候选人在开放问题下的思维框架——是直接跳到算法细节，还是先做需求分析和场景梳理。

### 期望回答方向：

优秀的候选人会先问清楚或主动分析以下问题，而不是立刻讨论算法：

1. **限流维度：** 按什么维度限流？用户 ID？IP？接口？租户（机构）？
2. **限流粒度：** 单机限流还是分布式全局限流？
3. **限流效果：** 超限后直接拒绝（返回 429）还是排队等待？
4. **使用形态：** 作为中间件（Middleware）嵌入 HTTP/RPC 框架，还是作为独立 SDK 被业务代码调用？
5. **动态配置：** 限流阈值是否需要动态调整（不重启服务）？

**评分：**
| 表现 | 分数 |
|------|------|
| 直接讲算法，不做需求分析 | 1 分 |
| 能提出 2-3 个关键问题 | 3 分 |
| 系统性分析场景、维度、形态，再设计方案 | 5 分 |

---

## 第二阶段：核心算法（8 分钟）

### 面试官追问：

> "好，场景明确了。现在请你对比几种常见的限流算法，分析各自的优缺点，然后说说你会选择哪种，为什么。"

### 参考答案：

#### 1. 固定窗口计数器（Fixed Window Counter）

```
|-------- 1s --------|-------- 1s --------|
|  count: 98/100     |  count: 0/100      |
                  ↑ 窗口边界
```

- **原理：** 将时间划分为固定窗口（如每秒），每个窗口维护一个计数器，请求到来时计数器 +1，超过阈值则拒绝。
- **优点：** 实现极简，内存占用小。
- **缺点：** **临界突刺问题** —— 在窗口交界处，前一个窗口的尾部和后一个窗口的头部可能累计产生 2 倍的突发流量。例如限制 100 QPS，但在第 0.9s-1.1s 这 200ms 内可能通过 200 个请求。
- **实现：** Redis `INCR + EXPIRE` 或内存 atomic counter。

#### 2. 滑动窗口计数器（Sliding Window Counter）

```
|---0.3s---|---0.3s---|---0.3s---|---0.3s---|
| sub: 30  | sub: 25  | sub: 35  | sub: 10  |
           |<----- 当前窗口 1s ----->|
           weighted count = 25 + 35 + 10 = 70
```

- **原理：** 将窗口细分为多个子窗口（如 1 秒分为 10 个 100ms 子窗口），滑动统计最近 1 秒内所有子窗口的请求总数。或用加权方式近似：`当前窗口计数 + 上一窗口计数 × 上一窗口剩余时间占比`。
- **优点：** 解决了固定窗口的临界突刺问题，平滑度好。
- **缺点：** 实现稍复杂，需要维护多个子窗口的计数。
- **实现：** Redis 的 Sorted Set（用时间戳作 score）或多个子 key。

#### 3. 漏桶算法（Leaky Bucket）

```
       请求流入（突发的）
          ↓ ↓ ↓ ↓ ↓
    ┌─────────────────┐
    │    漏桶 (队列)    │ ← 桶满则溢出（拒绝）
    │  ●  ●  ●  ●     │
    └──────┬──────────┘
           ↓ (恒定速率流出)
        处理请求
```

- **原理：** 请求进入一个固定容量的队列（桶），以恒定速率从队列中取出处理。桶满时新请求被拒绝。
- **优点：** 输出速率恒定，对后端系统保护效果最好，流量非常平滑。
- **缺点：** 无法处理合理的突发流量（即使系统空闲，也只能以固定速率处理）；请求在桶中等待，引入延迟。
- **适用场景：** 对后端保护要求极高，需要严格平滑流量的场景（如第三方 API 调用频率限制）。

#### 4. 令牌桶算法（Token Bucket）✅ 推荐

```
    以恒定速率 r 放入令牌
          ↓ ↓ ↓
    ┌─────────────────┐
    │   令牌桶 (容量 b)  │ ← 桶满则丢弃多余令牌
    │  ○  ○  ○  ○  ○  │
    └──────┬──────────┘
           ↓ 请求取走令牌
    有令牌 → 通过 ｜ 无令牌 → 拒绝
```

- **原理：** 以恒定速率向桶中添加令牌（最多存 b 个），每个请求消耗一个令牌。桶中有令牌则通过，无令牌则拒绝。
- **优点：** **允许一定程度的突发流量**（桶中积累的令牌可以应对短时突发）；长期平均速率由令牌生成速率决定；参数直观（速率 r + 桶容量 b）。
- **缺点：** 实现比固定窗口复杂。
- **适用场景：** 大多数在线服务限流的首选。

#### 5. 滑动窗口日志（Sliding Window Log）

- **原理：** 记录每个请求的精确时间戳，查询时统计最近窗口内的请求数。
- **优点：** 精确度最高。
- **缺点：** 内存消耗大（需存储每个请求的时间戳），高 QPS 下不实际。

#### 对比总结表：

| 算法 | 突发容忍 | 平滑度 | 内存开销 | 实现复杂度 | 精确度 |
|------|---------|--------|---------|-----------|--------|
| 固定窗口 | 差（临界突刺） | 低 | 极低 | 极低 | 低 |
| 滑动窗口计数 | 中 | 中 | 低 | 中 | 中 |
| 漏桶 | 无（严格平滑） | 高 | 中 | 中 | 高 |
| 令牌桶 | 好（可控突发） | 中-高 | 低 | 中 | 高 |
| 滑动窗口日志 | 好 | 高 | 高 | 低 | 极高 |

#### 选型建议：

> 对于 MCN 平台这种在线业务，推荐**令牌桶**作为默认策略——既能保护后端，又能容忍短时突发（如批量操作请求）。对于需要严格限频的场景（如外部 API 调用），可使用漏桶。

**评分：**
| 表现 | 分数 |
|------|------|
| 只知道 1-2 种算法，原理模糊 | 2 分 |
| 知道 3 种以上算法，能对比优缺点 | 5 分 |
| 对比全面，能结合业务场景做选型判断，理解突发流量容忍的权衡 | 8 分 |

---

## 第三阶段：单机实现（5 分钟）

### 面试官追问：

> "好，现在请你用 Go 实现一个单机版的令牌桶限流器。重点说清楚数据结构和核心逻辑，不需要逐行写代码但要体现关键设计。"

### 参考答案：

核心思路：**惰性计算** —— 不需要真的用 goroutine 定时往桶里放令牌（定时器有精度和性能问题）。每次请求到来时，根据距上次请求的时间差，一次性计算出应该补充多少令牌。

```go
type TokenBucket struct {
    mu         sync.Mutex
    rate       float64   // 每秒生成的令牌数
    capacity   float64   // 桶容量（最大突发量）
    tokens     float64   // 当前令牌数
    lastRefill time.Time // 上次补充令牌的时间
}

func NewTokenBucket(rate, capacity float64) *TokenBucket {
    return &TokenBucket{
        rate:       rate,
        capacity:   capacity,
        tokens:     capacity, // 初始满桶
        lastRefill: time.Now(),
    }
}

func (tb *TokenBucket) Allow() bool {
    tb.mu.Lock()
    defer tb.mu.Unlock()

    now := time.Now()
    elapsed := now.Sub(tb.lastRefill).Seconds()

    // 惰性补充令牌
    tb.tokens += elapsed * tb.rate
    if tb.tokens > tb.capacity {
        tb.tokens = tb.capacity
    }
    tb.lastRefill = now

    if tb.tokens >= 1 {
        tb.tokens--
        return true
    }
    return false
}
```

**关键追问点：**

| 追问 | 期望回答 |
|------|---------|
| "为什么用 `sync.Mutex` 而不是 `atomic`？" | 因为需要同时读写 tokens 和 lastRefill 两个变量，单个 atomic 操作无法保证两个字段的一致性。如果追求极致性能，可以用 CAS + 将 tokens 和 time 打包为一个 int64 来做无锁实现，但复杂度高且收益有限。 |
| "如果限流维度是每个用户一个桶，如何管理大量桶？" | 用 `sync.Map` 或分片的 map 存储 `userID → *TokenBucket`。需要定期清理长时间不活跃的桶（TTL 过期 + 异步清理 goroutine），防止内存泄漏。 |
| "Go 标准库有没有现成的？" | `golang.org/x/time/rate` 包提供了基于令牌桶的 `rate.Limiter`，支持 `Allow()`（非阻塞）、`Wait(ctx)`（阻塞等待）、`Reserve()`（预约令牌）。生产环境推荐基于它做二次封装。 |

**评分：**
| 表现 | 分数 |
|------|------|
| 用定时器真的放令牌，不知道惰性计算 | 2 分 |
| 能写出惰性计算的核心逻辑，并发安全 | 5 分 |
| 惰性计算 + 了解 `x/time/rate` + 能讨论多桶管理和内存回收 | 7 分 |

---

## 第四阶段：分布式限流（8 分钟）— 核心难点

### 面试官追问：

> "单机限流有个问题：你们的服务是多 Pod 部署的，单机限流只能保护单个实例。如果要做全局限流——比如某个机构的 API 调用频率全局不能超过 1000 QPS——你怎么设计？"

### 参考答案：

#### 方案一：中心化 Redis 限流（最常用）

```
  Pod 1 ──┐              ┌── 判定结果
  Pod 2 ──┼── Redis ────┤
  Pod 3 ──┘   (中心计数)  └── 原子操作保证全局一致
```

**实现方式：Lua 脚本保证原子性**

```lua
-- 令牌桶的 Redis Lua 实现
local key = KEYS[1]
local rate = tonumber(ARGV[1])        -- 每秒令牌数
local capacity = tonumber(ARGV[2])    -- 桶容量
local now = tonumber(ARGV[3])         -- 当前时间戳(ms)
local requested = tonumber(ARGV[4])   -- 请求令牌数

local bucket = redis.call('HMGET', key, 'tokens', 'last_refill')
local tokens = tonumber(bucket[1]) or capacity
local last_refill = tonumber(bucket[2]) or now

-- 计算补充的令牌
local elapsed = (now - last_refill) / 1000
local new_tokens = math.min(capacity, tokens + elapsed * rate)

if new_tokens >= requested then
    new_tokens = new_tokens - requested
    redis.call('HMSET', key, 'tokens', new_tokens, 'last_refill', now)
    redis.call('PEXPIRE', key, 2000)  -- 2秒过期，防止僵尸key
    return 1  -- 允许
else
    redis.call('HMSET', key, 'tokens', new_tokens, 'last_refill', now)
    redis.call('PEXPIRE', key, 2000)
    return 0  -- 拒绝
end
```

**优点：** 全局精确、实现相对简单、强一致。
**缺点：** 每次请求都要访问 Redis，增加 1-2ms 的网络延迟；Redis 成为性能瓶颈和单点。

#### 方案二：本地预分配 + 中心化协调（高性能方案）

```
              ┌─── Pod 1: 本地桶 300 QPS
Redis/协调器 ──┼─── Pod 2: 本地桶 300 QPS
              └─── Pod 3: 本地桶 400 QPS
                              总计 1000 QPS
```

- **原理：** 将全局配额预先分配到各 Pod，每个 Pod 在本地做限流判定（零网络开销）。
- **配额分配：** 简单方案为均分（1000 QPS / 3 Pods ≈ 333 QPS/Pod）；高级方案基于各 Pod 的历史流量做加权分配。
- **动态调整：** 定期（如每 5 秒）向中心节点上报消耗情况并重新分配配额，解决流量不均问题。
- **优点：** 判定在本地完成，零网络延迟；Redis 压力极小。
- **缺点：** 不精确（有误差窗口）；实现复杂；Pod 扩缩容时需要重新分配。

#### 方案三：滑动窗口 + Redis Sorted Set

```lua
-- 用 Sorted Set 实现精确滑动窗口
local key = KEYS[1]
local now = tonumber(ARGV[1])
local window = tonumber(ARGV[2])  -- 窗口大小(ms)
local limit = tonumber(ARGV[3])

-- 清除窗口外的旧记录
redis.call('ZREMRANGEBYSCORE', key, 0, now - window)

local count = redis.call('ZCARD', key)
if count < limit then
    redis.call('ZADD', key, now, now .. '-' .. math.random())
    redis.call('PEXPIRE', key, window)
    return 1
else
    return 0
end
```

**优点：** 精确的滑动窗口，无临界突刺。
**缺点：** 每个请求在 Redis 中存一条记录，高 QPS 下内存消耗大；ZCARD 和 ZREMRANGEBYSCORE 的时间复杂度较高。

#### 方案选型建议：

| 场景 | 推荐方案 |
|------|---------|
| 大多数业务接口（对精度要求不极端） | 方案一：Redis Lua 令牌桶 |
| 超高 QPS 且延迟敏感（如核心搜索链路） | 方案二：本地预分配 |
| 低 QPS 但需要精确计数（如外部 API 配额） | 方案三：Redis Sorted Set |

**关键追问与期望回答：**

| 追问 | 期望回答 |
|------|---------|
| "Redis 挂了怎么办？" | 降级为单机限流（基于本地令牌桶，按 Pod 数均分阈值估算）。限流组件不应该成为可用性的瓶颈——Redis 不可用时，宁可放行也不要因为限流组件故障导致所有请求被拒。 |
| "Lua 脚本的原子性是怎么保证的？" | Redis 单线程执行模型保证了 Lua 脚本的原子性——脚本执行期间不会被其他命令打断。但要注意脚本不能过长，否则会阻塞其他请求。 |
| "Redis Cluster 模式下 Lua 脚本有什么限制？" | Lua 脚本中涉及的所有 key 必须在同一个 slot（分片），否则会报错。可以通过 hash tag（如 `{ratelimit}:user:123`）强制同一前缀的 key 落到同一 slot。 |

**评分：**
| 表现 | 分数 |
|------|------|
| 只知道 Redis INCR 计数，不了解原子性问题 | 2 分 |
| 能用 Redis Lua 脚本实现令牌桶/滑动窗口 | 5 分 |
| 了解多种分布式方案并能对比选型，考虑了降级和 Redis Cluster 限制 | 8 分 |

---

## 第五阶段：工程化设计（5 分钟）

### 面试官追问：

> "算法和分布式方案都有了，现在要把它做成一个真正可复用的组件给团队用。从工程角度，你会怎么设计这个组件的接口和架构？"

### 参考答案：

#### 1. 组件架构分层

```
┌──────────────────────────────────────────────┐
│              应用层（业务代码）                  │
├──────────────────────────────────────────────┤
│        Middleware 层（Hertz / Kitex 中间件）     │
│   ┌──────────────┐  ┌───────────────────┐    │
│   │ HTTP Middleware│  │  RPC Middleware    │    │
│   └──────┬───────┘  └───────┬───────────┘    │
│          └────────┬─────────┘                 │
├──────────────────┬───────────────────────────┤
│           Limiter Core（核心限流引擎）          │
│   ┌──────────┐ ┌──────────┐ ┌──────────┐    │
│   │ TokenBucket│ │SlidingWin│ │ LeakyBucket│  │
│   └──────────┘ └──────────┘ └──────────┘    │
├──────────────────────────────────────────────┤
│           Backend（存储后端抽象）               │
│   ┌──────────┐ ┌──────────┐ ┌──────────┐    │
│   │  Memory   │ │   Redis  │ │  Custom  │    │
│   └──────────┘ └──────────┘ └──────────┘    │
├──────────────────────────────────────────────┤
│           Config（配置中心对接）                │
│   ┌──────────────────────────────────────┐   │
│   │    动态配置（热加载，不重启生效）          │   │
│   └──────────────────────────────────────┘   │
└──────────────────────────────────────────────┘
```

#### 2. 核心接口设计

```go
// 限流器接口 —— 策略模式，可替换算法
type Limiter interface {
    Allow(ctx context.Context, key string) (*Result, error)
}

type Result struct {
    Allowed   bool          // 是否允许
    Remaining int64         // 剩余配额
    ResetAt   time.Time     // 配额重置时间
    RetryAfter time.Duration // 建议重试等待时间（用于返回 Retry-After 头）
}

// 限流规则配置
type Rule struct {
    Key       string        // 限流维度标识（如 "user:{uid}", "api:/v1/export"）
    Rate      float64       // 速率（令牌/秒 或 请求/窗口）
    Capacity  int64         // 桶容量 / 窗口上限
    Window    time.Duration // 窗口大小（滑动窗口时使用）
    Algorithm string        // "token_bucket" | "sliding_window" | "leaky_bucket"
}

// Key 提取器 —— 从请求中提取限流维度
type KeyExtractor func(ctx context.Context) string

// 内置 Key 提取器
func ByUserID() KeyExtractor   { ... }  // 按用户 ID
func ByIP() KeyExtractor       { ... }  // 按 IP
func ByAPI() KeyExtractor      { ... }  // 按接口路径
func ByComposite(extractors ...KeyExtractor) KeyExtractor { ... } // 组合维度
```

#### 3. Middleware 集成

```go
// Hertz HTTP 中间件
func RateLimitMiddleware(limiter Limiter, keyFn KeyExtractor) app.HandlerFunc {
    return func(ctx context.Context, c *app.RequestContext) {
        key := keyFn(ctx)
        result, err := limiter.Allow(ctx, key)

        if err != nil {
            // 限流组件故障 → 降级放行，记录日志告警
            c.Next(ctx)
            return
        }

        // 标准化响应头
        c.Header("X-RateLimit-Remaining", strconv.FormatInt(result.Remaining, 10))
        c.Header("X-RateLimit-Reset", strconv.FormatInt(result.ResetAt.Unix(), 10))

        if !result.Allowed {
            c.Header("Retry-After", strconv.Itoa(int(result.RetryAfter.Seconds())))
            c.AbortWithStatusJSON(429, map[string]string{
                "error": "rate limit exceeded",
            })
            return
        }
        c.Next(ctx)
    }
}

// 使用示例
h := server.Default()
h.Use(RateLimitMiddleware(
    NewRedisTokenBucket(redisClient, Rule{Rate: 100, Capacity: 200}),
    ByUserID(),
))
```

#### 4. 可观测性

```
限流组件应内置以下指标（Prometheus metrics）：
- ratelimit_total{key, result="allowed|rejected"}  — 请求总数
- ratelimit_latency_seconds{backend="redis|memory"} — 限流判定耗时
- ratelimit_current_tokens{key}                      — 当前令牌数（仪表盘）
- ratelimit_redis_errors_total                        — Redis 异常次数
```

#### 5. 动态配置热加载

- 与配置中心（如 Apollo、字节内部的 TCC）对接，监听限流规则变更。
- 规则变更时无需重启服务，自动生效。
- 支持紧急开关：一键关闭限流（故障时兜底）。

**评分：**
| 表现 | 分数 |
|------|------|
| 只考虑了算法实现，没有工程化思考 | 1 分 |
| 有接口抽象、Middleware 集成的设计 | 4 分 |
| 完整的分层架构 + 接口设计 + 降级策略 + 可观测性 + 动态配置 | 7 分 |

---

## 第六阶段：进阶追问（可选，视时间和候选人水平）

### 追问 1：多维度组合限流

> "如果同一个请求需要同时满足多个限流规则（如全局 QPS 限制 + 单用户 QPS 限制 + 单接口 QPS 限制），你怎么设计？"

**参考答案：**
- **责任链模式：** 将多个限流规则串联成链，请求依次通过每个规则，任一规则拒绝则整体拒绝。
- **优化点：** 先检查最可能拒绝的规则（如用户级限流通常比全局限流更容易触发），提前终止减少不必要的 Redis 调用。
- **原子性考虑：** 如果需要"全部通过才扣减"的语义，可以将多个规则的扣减合并到一个 Lua 脚本中。但通常不需要这么严格——先扣减后回补（允许短时的少量误差）也是可接受的。

### 追问 2：热 Key 问题

> "如果某个大机构的请求量特别集中，导致对应的 Redis 限流 key 成为热 key，怎么处理？"

**参考答案：**
- **本地缓存 + Redis 混合：** 本地维护一个小容量的令牌桶做一级限流，超过本地配额后再访问 Redis。绝大多数请求在本地判定，Redis 只处理溢出流量。
- **Key 分片：** 将一个热 key 拆分为多个子 key（如 `ratelimit:org:123:shard:0~9`），请求随机分散到子 key，最后汇总判定。
- **批量获取令牌：** 每次从 Redis 获取一批令牌（如 50 个），本地分发消耗，减少 Redis 请求频率。

### 追问 3：与你在字节的实际经验结合

> "你在 MCN 平台做的限流实际是怎么实现的？Kitex 框架自带的限流能力你有没有用过？能否对比一下？"

**期望回答：**
- Kitex 内置了基于连接数和 QPS 的基础限流（`server.WithLimit`），但粒度较粗（只能做服务级别限流）。
- 更细粒度的限流（如用户级、机构级）需要自行实现，通常在 Middleware 层接入。
- 字节内部可能有统一的限流平台（如类似 Sentinel 的组件），可以直接接入。

---

## 综合评分表

| 阶段 | 考察维度 | 满分 | 权重 |
|------|---------|------|------|
| 第一阶段：需求分析 | 系统思维、沟通能力 | 5 分 | 10% |
| 第二阶段：算法对比 | 技术深度、知识广度 | 8 分 | 20% |
| 第三阶段：单机实现 | Go 编码能力、并发安全 | 7 分 | 20% |
| 第四阶段：分布式限流 | 分布式系统设计能力 | 8 分 | 25% |
| 第五阶段：工程化设计 | 架构设计、框架思维 | 7 分 | 20% |
| 第六阶段：进阶追问 | 综合深度、实战经验 | 加分项 | 5% |

### 最终评级标准：

| 综合得分（加权） | 评级 | 判断 |
|-----------------|------|------|
| ≥ 85% | A（优秀） | 能独立设计并落地生产级限流组件，对分布式场景有深入理解 |
| 70%-84% | B（良好） | 掌握核心算法和基本的分布式方案，工程化能力有待加强 |
| 55%-69% | C（合格） | 了解常见算法，但缺少分布式和工程化的思考 |
| < 55% | D（不足） | 基础知识薄弱，不满足岗位要求 |

---

## 面试官操作指南

### 时间分配建议（共 25 分钟）

```
[0-5min]  第一阶段：开放提问，观察思维框架
[5-13min] 第二阶段：算法对比（重点，花够时间）
[13-18min] 第三阶段：单机实现（写代码或伪代码）
[18-25min] 第四阶段：分布式限流（核心难点，深入追问）
[如有余力] 第五/六阶段：工程化 + 进阶追问
```

### 追问策略

- **候选人表现强：** 快速过 1-3 阶段，重点在 4-6 阶段深挖分布式和工程化能力。
- **候选人表现一般：** 在 2-3 阶段多给引导，通过追问帮助候选人展示最好水平。
- **关键区分度：** 第四阶段（分布式限流）是区分"合格"和"优秀"的关键。能自主提出 Redis Lua 方案并讨论其局限性的，基本可评为 B 及以上。

### 红旗信号（可能不合格）

- 完全不知道令牌桶和漏桶的区别。
- 无法写出基本的并发安全代码（忘记加锁或用错同步原语）。
- 不理解为什么需要分布式限流（"单机限流不就够了吗"）。
- 不考虑限流组件本身的故障降级（"Redis 挂了就拒绝所有请求"）。

### 绿旗信号（优秀候选人）

- 开场就做需求分析，不急于跳到技术细节。
- 能从算法 → 单机 → 分布式 → 工程化逐层递进，思路清晰。
- 主动提出降级策略、可观测性、动态配置等生产必备能力。
- 能结合实际业务经验（MCN 平台）举例说明限流的应用场景和踩过的坑。
