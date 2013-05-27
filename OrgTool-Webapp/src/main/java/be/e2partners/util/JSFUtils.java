package be.e2partners.util;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

public final class JSFUtils {
	private JSFUtils() {
	}

	public static void addMessage(FacesMessage.Severity severity, String key) {
		String bundle = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		String msg = ResourceBundle.getBundle(bundle, locale).getString(key);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, msg));
	}

    public static void addMessageDirect(FacesMessage.Severity severity, String value) {

		FacesContext.getCurrentInstance().addMessage(severity.toString(), new FacesMessage(severity, value, value));
	}

	public static void addMessageWithArguments(FacesMessage.Severity severity, String key, String[] args) {
		addMessageWithArguments(severity, null, key, args);
	}

	public static void addMessageWithArguments(FacesMessage.Severity severity, String clientId, String key,
			String[] args) {
		String bundle = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle resbundle = ResourceBundle.getBundle(bundle, locale);
		String msg = resbundle.getString(key);
//		for (int i = 0; i < args.length; i++) {
//			msg = StringUtils.replace(msg, "{" + i + "}", args[i]);
//		}

		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(severity, msg, msg));
	}
}
