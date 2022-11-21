package vn.techmaster.personalmanagementcrud.localization;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import vn.techmaster.personalmanagementcrud.model.LocalizeString;

import java.util.HashMap;
import java.util.Locale;

public class DBMessageSource implements MessageSource {
    private HashMap<String, LocalizeString> localizeDB;

    public DBMessageSource(){
        localizeDB = new HashMap<>();
        localizeDB.put("name", new LocalizeString("Name", "Tên"));

        localizeDB.put("job",new LocalizeString("Job","Công việc"));

        localizeDB.put("birthdate",new LocalizeString("Birthdate","Ngày sinh"));

        localizeDB.put("Select a photo",new LocalizeString("Select a photo","Chọn ảnh tải lên"));
        localizeDB.put("Submit",new LocalizeString("Submit","Lưu"));
    }

    private String getString(String code, String defaultMessage, Locale locale) {
        LocalizeString localizeString = localizeDB.get(code);
        if(localizeString == null) {
            return defaultMessage;
        }
        switch (locale.getLanguage()) {
            case "vn":
                return localizeString.getVn();
            case "en":
                return localizeString.getEn();
            default:
                return defaultMessage;
        }
    }

    @Override
    public String getMessage(String code, Object[] objects, String defaultMessage, Locale locale) {
        return getString(code, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] objects, Locale locale) throws NoSuchMessageException {
        return getString(code, code, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return resolvable.getDefaultMessage();
    }
}
