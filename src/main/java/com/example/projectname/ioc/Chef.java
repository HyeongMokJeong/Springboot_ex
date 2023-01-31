package com.example.projectname.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    // 셰프가 식쟤료 공장을 알고 있음
    private IngredientFactory ingredientFactory;

    // 셰프가 식쟤료 공장과 협업하기 위한 DI
    // @Component 클래스에 생성자가 1개인 경우, 자동 @Autowired가 붙게 됨 -> ioc 컨테이너 안의 ingredientFactory 가져옴
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 요리 쟤료 준비
        Ingredient ingredient = ingredientFactory.get(menu);

        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
