package javakurs.library.web;

import java.io.Serializable;
import java.util.Locale;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
public class LanguageBean implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(LanguageBean.class);
	
	private Locale locale;
	
	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getLanguage() {
		return locale.getLanguage();
	}
	
	public void setLanguage(String language) {
		
		logger.info("Language: {}", language);
		
		locale = new Locale("sr");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
	
	

}
