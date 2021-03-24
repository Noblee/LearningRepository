package designpattern.templateMethod;

//炒蒜蓉菜心的类
public class ConcreteClass_CaiXin extends BaseAbstractClass {

    @Override
    void pourVegetable() {
        System.out.println("下锅的蔬菜是菜心");
    }
    @Override
    void pourSauce() {
        System.out.println("下锅的酱料是蒜蓉");
    }
}