/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author justynafraczek
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/NewsQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements MessageListener {
    
    @PersistenceContext
    private EntityManager em;
    public NewsMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        if (message instanceof TextMessage) {
            try {
                msg = (TextMessage) message;
                NewsItem e = new NewsItem();
                String[] parts = msg.getText().split("\\|");
                e.setHeading(parts[0]);
                e.setBody(parts[1]);
                em.persist(e);
            } catch (JMSException ex) {
                Logger.getLogger(NewsMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
    
