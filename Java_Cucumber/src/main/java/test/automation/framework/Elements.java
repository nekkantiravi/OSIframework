package test.automation.framework;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static test.automation.framework.Page.getCurrentPage;
import static test.automation.framework.Page.getCurrentPageName;
import static test.automation.framework.Runner.log;

public final class Elements {

    public static WebElement getWebElement(String element) {
        if (getCurrentPage() == null) {
            throw new RuntimeException("Not on valid page!");
        }
        String pageClass = getCurrentPageName();
        try {
            return ((WebElement) getCurrentPage().getClass().getDeclaredField(element).get(getCurrentPage()));
        } catch (NoSuchFieldException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not declared in " + pageClass);
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not accessible from " + pageClass);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not an element in " + pageClass);
        }
    }

    public static List<WebElement> getWebElements(String element) {
        if (getCurrentPage() == null) {
            throw new RuntimeException("Not on valid page!");
        }
        String pageClass = getCurrentPageName();
        try {
            return ((List<WebElement>) getCurrentPage().getClass().getDeclaredField(element).get(getCurrentPage()));
        } catch (NoSuchFieldException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not declared in " + pageClass);
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not accessible from "+ pageClass);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not an element list in " + pageClass);
        }
    }

    public static TypifiedElement getElement(String element) {
        try {
            return ((TypifiedElement) getWebElement(element));
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not an element in " + getCurrentPageName());
        }
    }

    public static CheckBox getCheckBox(String checkBox) {
        try {
            return (CheckBox) getElement(checkBox);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(checkBox + " is not an check box in " + getCurrentPageName());
        }
    }

    public static Form getForm(String form) {
        try {
            return (Form) getElement(form);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(form + " is not an form in " + getCurrentPageName());
        }
    }

    public static Image getImage(String image) {
        try {
            return (Image) getElement(image);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(image + " is not an image in " + getCurrentPageName());
        }
    }

    public static Radio getRadio(String radio) {
        try {
            return (Radio) getElement(radio);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(radio + " is not an radio in " + getCurrentPageName());
        }
    }

    public static Select getSelect(String select) {
        try {
            return (Select) getElement(select);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(select + " is not an select in " + getCurrentPageName());
        }
    }

    public static Table getTable(String table) {
        try {
            return (Table) getElement(table);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(table + " is not an table in " + getCurrentPageName());
        }
    }

    public static TextInput getTextInput(String textInput) {
        try {
            return (TextInput) getElement(textInput);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(textInput + " is not an text input in " + getCurrentPageName());
        }
    }

    public static FileInput getFileInput(String fileInput) {
        try {
            return (FileInput) getElement(fileInput);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(fileInput + " is not an file input in " + getCurrentPageName());
        }
    }

    public static HtmlElement getPanel(String panel) {
        try {
            return ((HtmlElement) getWebElement(panel));
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(panel + " is not an panel in " + getCurrentPageName());
        }
    }

    public static WebElement getWebElement(String element, String panel) {
        HtmlElement panelE = getPanel(panel);
        String panelClass = panelE.getClass().getSimpleName();
        try {
            return ((WebElement) panelE.getClass().getDeclaredField(element).get(panelE));
        } catch (NoSuchFieldException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not declared in " + panelClass);
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not accessible from " + panelClass);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not an element in " + panelClass);
        }
    }

    public static List<WebElement> getWebElements(String element, String panel) {
        HtmlElement panelE = getPanel(panel);
        String panelClass = panelE.getClass().getSimpleName();
        try {
            return ((List<WebElement>) panelE.getClass().getDeclaredField(element).get(panelE));
        } catch (NoSuchFieldException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not declared in " + panelClass);
        } catch (IllegalAccessException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not accessible from " + panelClass);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not a element list in " + panelClass);
        }
    }

    public static TypifiedElement getElement(String element, String panel) {
        try {
            return ((TypifiedElement) getWebElement(element, panel));
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(element + " is not an element in " + panel);
        }
    }

    public static CheckBox getCheckBox(String checkBox, String panel) {
        try {
            return (CheckBox) getElement(checkBox, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(checkBox + " is not an check box in " + panel);
        }
    }

    public static Form getForm(String form, String panel) {
        try {
            return (Form) getElement(form, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(form + " is not an form in " + panel);
        }
    }

    public static Image getImage(String image, String panel) {
        try {
            return (Image) getElement(image, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(image + " is not an image in " + panel);
        }
    }

    public static Radio getRadio(String radio, String panel) {
        try {
            return (Radio) getElement(radio, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(radio + " is not an radio in " + panel);
        }
    }

    public static Select getSelect(String select, String panel) {
        try {
            return (Select) getElement(select, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(select + " is not an select in " + panel);
        }
    }

    public static Table getTable(String table, String panel) {
        try {
            return (Table) getElement(table, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(table + " is not an table in " + panel);
        }
    }

    public static TextInput getTextInput(String textInput, String panel) {
        try {
            return (TextInput) getElement(textInput, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(textInput + " is not an text input in " + panel);
        }
    }

    public static FileInput getFileInput(String fileInput, String panel) {
        try {
            return (FileInput) getElement(fileInput, panel);
        } catch (ClassCastException e) {
            log().log(Level.WARNING, e.getMessage());
            throw new RuntimeException(fileInput + " is not an file input in " + panel);
        }
    }
}
