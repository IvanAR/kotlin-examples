import model.JavaMage;
import model.Mage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Ivan A. Reffatti
 * @since 26/04/18
 */
public class JavaLanguageUnitTests {

    private final JavaMage gandalfTheGray = new JavaMage("Gandalf", "The Gray", 2019);

    @Test
    @DisplayName("Null pointers throw exception and you dont even see them")
    public void nullPointersThrowExcepionAndYouCantSeeIt() {
        assertThrows(NullPointerException.class, gandalfTheGray::getFatherName);
    }

    @Test
    @DisplayName("Null vars must be explicit checked")
    public void nullVarsMustBeExplicitChecked() {
        final String fatherName = gandalfTheGray.getFatherName();
        assertNull(fatherName != null ? fatherName.length() : null);
    }

    @Test
    @DisplayName("Todo is a coment and we must throw exceptions by hand")
    public void todoIsAComentAndWeMustThrowExceptionByHand() {
        // TODO I think we should implement something here!
        assertThrows(NotImplementedException.class, () -> {throw new NotImplementedException();});
    }

    public Mage createMage() {
        final Mage mage = new Mage("Radagast", "The Brown", 2000);
        mage.setFatherName("J. R. R. Tolkien");
        return mage;
    }

}


