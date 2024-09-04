package util;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class PassGenerator {
    public String generatePassword() {
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        UCR.setNumberOfCharacters(1);

        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        LCR.setNumberOfCharacters(5);

        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        DR.setNumberOfCharacters(1);

        CharacterData specialChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return "ERROR_SPECIAL_CHAR";
            }

            @Override
            public String getCharacters() {
                return "@#$%&";
            }
        };

        CharacterRule SR = new CharacterRule(specialChars);
        SR.setNumberOfCharacters(1);

        PasswordGenerator pg = new PasswordGenerator();

        return pg.generatePassword(8, SR, LCR, UCR, DR);
    }
}
