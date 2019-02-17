package pavlo.juniortask.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pavlo.juniortask.Model.Email;

import java.beans.PropertyEditorSupport;

@Component
public class PhoneEdiror extends PropertyEditorSupport {
    @Autowired
    private EmailService emailService;

    @Override
    public void setAsText(String email) throws IllegalArgumentException {
        Email email1 = new Email();
        email1.setEmail(email);
        setValue(email1);
    }
}
