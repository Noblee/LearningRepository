package designpattern.templateMethod;

//炒手撕包菜的类
public class ConcreteClass_BaoCai extends BaseAbstractClass {
    @Override
    void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }
    @Override
    void pourSauce() {
        System.out.println("下锅的酱料是辣椒");
    }
}