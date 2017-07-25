package com.example.screens;

import com.example.fields.EntryField;
import com.example.fields.LabelField;
import com.example.session.Session;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;
import com.jagacy.util.Logger;

/**
 * Created by upgundecha on 14/10/16.
 */
public class THDHomeScreen {

    private Session session;
    private String screenCrc = "0xc2afab99";

    // Screen fields
    private LabelField waitForLabel =
            new LabelField(4, 28, "North American Systems");
    private EntryField applicationEntryField = new EntryField(22, 38);
    private EntryField loginId = new EntryField(0, 26);

    public THDHomeScreen(final Session s) throws JagacyException {
        this.session = s;
        if (!session.waitForTextLabel(waitForLabel)) {
            throw new IllegalStateException("Not Home screen!");
        }
        if (session.getCrc32() != Long.decode(screenCrc)) {
            throw new IllegalStateException("Home Screen has been changed!");
        }
    }

    /**
     * Open Phonbook Menu screen.
     *
     * @return Phonbook Menu Screen
     * @throws JagacyException JagacyException
     */
    public THDMenuScreen typetso() throws JagacyException, InterruptedException {
        session.setEntryFieldValue(applicationEntryField, "tso");
        session.writeKey(Key.ENTER);
        session.waitForChange(session.DEFAULT_TIMEOUT);
        return new THDMenuScreen(session);
    }
}