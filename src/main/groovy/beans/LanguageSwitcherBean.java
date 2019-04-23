package beans;

import grails.plugins.primefaces.MessageSourceBean;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
* The Bean will work with MessageSourceBean,it provide the language select value for user.
* And let user can setting the default language in here.
* source file https://github.com/andreaminnucci/primefaces
* Modify &amp; Improve: Prominic -Bing Li
* Data: 2019-04-21
*/
@ManagedBean
public class LanguageSwitcherBean {

    @ManagedProperty(value = "#{message}")
    private MessageSourceBean message;
     
    private String selectedLanguage;
    private List<String> languages;

    public MessageSourceBean getMessage() {
        return message;
    }

    public void setMessage(MessageSourceBean message) {
        this.message = message;
    }

    public String getSelectedLanguage() {
        selectedLanguage = message.getLocale().getLanguage();
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        message.setLocale(selectedLanguage);
        this.selectedLanguage = selectedLanguage;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getLanguages() {
        if (languages == null) {
            languages = new ArrayList<String>() {
                {
                    add("it");
                    add("en");
                }
            };
        }
        return languages;
    }

}