package ru.nasa.front.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import ru.nasa.front.components.Toolbar;
import ru.nasa.front.components.ToolbarItemMainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.nasa.front.constants.Constants.PAGE_LOAD_TIME;

public class MainPage extends AbstractPage<MainPage> {
    private Toolbar<ToolbarItemMainPage> toolbar = new Toolbar<>($("#headerContent.usa-nav-primary"), ToolbarItemMainPage::new);
    private final String fName = "user_first_name";
    private final String lName = "user_last_name";
    private final String email = "user_email";

    public MainPage() {
        super();
        this.url = "https://api.nasa.gov/";
    }

    public SelenideElement getFNameField() {
        return $(String.format("input#%s", fName));
    }

    public SelenideElement getLNameField() {
        return $(String.format("input#%s", lName));
    }

    public SelenideElement getEmailField() {
        return $(String.format("input#%s", email));
    }

    public SelenideElement getFNameLabel() {
        return $(String.format("label[for=%s]", fName));
    }

    public SelenideElement getLNameLabel() {
        return $(String.format("label[for=%s]", lName));
    }

    public SelenideElement getEmailLabel() {
        return $(String.format("label[for=%s]", email));
    }

    public SelenideElement getApplicationUrl() {
        return $("input#user_use_description");
    }

    public SelenideElement getSignupButton() {
        return $("button.btn.btn-lg.btn-primary");
    }

    public MainPage navigate() {
        return super.navigate(this.getClass());
    }

    public SelenideElement getInfoPic() {
        return $("img#infoPic.infoDiv");
    }

    public SelenideElement getTextIconElement() {
        return $("label#infoTab");
    }

    public SelenideElement getHighlightedElement() {
        return $(".usa-section");
    }

    public SelenideElement getTextError() {
        return $("li.parsley-required");
    }

    public SelenideElement getCodeApiKey() {
        return $("code.signup-key");
    }

    public SelenideElement getHeaderNav() {
        return $("#headerNav.usa-nav");
    }

    public SelenideElement getListErrors() {
        return $("ul.parsley-errors-list");
    }

    public SelenideElement getToolbarSelectedItem() {
        return toolbar.getSelected();
    }

    public ToolbarItemMainPage findLinkToolbar(String toolbarName) {
        return toolbar.getSectionByName(toolbarName);
    }

    public Point findPointH2(String name) {
        SelenideElement h2Title = $$("h2").find(have(text(name)));
        return h2Title.toWebElement().getLocation();
    }


    @Override
    public MainPage waitPageLoaded() {
        $("div#apidatagov_signup").waitWhile(text("Loading signup form..."), PAGE_LOAD_TIME);
        return this;
    }

    public MainPage waitPageScrolled() {
        getToolbarSelectedItem().waitUntil(cssClass("currentDiv"), PAGE_LOAD_TIME);
        return this;
    }

    public MainPage waitPageLoadingSignup() {
        getCodeApiKey().waitUntil(exist, PAGE_LOAD_TIME);
        return this;
    }
}
