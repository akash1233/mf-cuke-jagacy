package com.example.screens;

import com.example.fields.EntryField;
import com.example.fields.LabelField;
import com.example.session.Session;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;

/**
 * Created by dharma on 7/25/17.
 */
public class THDMenuScreen {
    private Session session;
    private String screenCrc = "0xd09650bd";

    // Screen fields
    private LabelField waitForLabel =
            new LabelField(0, 1, "IKJ56700A ENTER USERID -");
    private EntryField actionEntryField = new EntryField(2, 1);

    public THDMenuScreen(final Session s) throws JagacyException {
        this.session = s;
        if (!session.waitForTextLabel(waitForLabel)) {
            throw new IllegalStateException("Not THD Main Menu screen!");
        }

        if (session.getCrc32() != Long.decode(screenCrc)) {
            throw new IllegalStateException("THD Menu "
                    + "Screen has been changed!");
        }
    }

    /**
     * Open Phonbook Search screen by calling the action.
     *
     * @return Phonbook Search screen
     * @throws JagacyException Jagacy Exception
     */
    public final THDSearchScreen typeusername()
            throws JagacyException {
        session.setEntryFieldValue(actionEntryField, "");
        session.writeKey(Key.ENTER);
        session.waitForChange(session.DEFAULT_TIMEOUT);
        return new THDSearchScreen(session);
    }
}
