#include<bits/stdc++.h>
using namespace std;
struct Tree
{
	int left , right;
	int max, sum;
};
Tree tree[400006];
void build(int id, int l, int r){
	tree[id].left = l;
	tree[id].right = r;
	if (l != r) {
		int mid = (l + r) / 2;
		build(id * 2, l, mid);
		build(id * 2 + 1, mid + 1, r);
		tree[id].sum = tree[id * 2].sum + tree[id * 2 + 1].sum;
		tree[id].max = max(tree[id * 2].max, tree[id * 2 + 1].max);
	}
	else{
		cin >> tree[id].max;
		tree[id].sum = tree[id].max;
	}
}
void update(int id, int pos, int val)
{
	if (tree[id].left == tree[id].right && tree[id].left == pos) //р╤вс╫з╣Ц
	{
		tree[id].max = tree[id].sum = val;
	}
	else
	{
		int mid = (tree[id].left + tree[id].right) / 2;
		if (pos <= mid) update(2 * id, pos, val);
		else update(2 * id + 1, pos, val);
		tree[id].max = max(tree[id * 2].max, tree[id * 2 + 1].max);
		tree[id].sum = tree[id * 2].sum + tree[id * 2 + 1].sum;
	}
}
int query(int id, int l , int r)
{
	if (tree[id].right == r && tree[id].left == l)
		return tree[id].max;
	else
	{
		int mid = (tree[id].right + tree[id].left) / 2;
		if (mid >= r)

			return query(id * 2, l, r);
		else if (l > mid)
			return query(id * 2 + 1, l, r);
		else
			return max(query(id * 2, l, mid), query(id * 2 + 1, mid + 1, r));
	}
}
int querysum(int id, int l , int r)
{
	if (tree[id].right == r && tree[id].left == l)
		return tree[id].sum;
	else
	{
		int mid = (tree[id].right + tree[id].left) / 2;
		if (mid >= r)
			return querysum(id * 2, l, r);
		else if (l > mid)
			return querysum(id * 2 + 1, l, r);
		else
			return querysum(id * 2, l, mid) + querysum(id * 2 + 1, mid + 1, r);
	}
}
int main()
{
	int n, m;
	cin >> n >> m;
	build(1, 1, n);
	for (int i = 0; i < m; i++)
	{
		int temp, a, b;
		cin >> temp >> a >> b;
		switch (temp)
		{
		case 1:
			update(1, a, b);
			break;
		case 2:
			cout << querysum(1, a, b) << endl;
			break;
		case 3:
			cout << query(1, a, b) << endl;
			break;
		}
	}
	return 0;
}
