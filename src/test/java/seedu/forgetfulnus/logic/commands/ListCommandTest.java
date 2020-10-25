package seedu.forgetfulnus.logic.commands;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.showFlashCardsAtIndex;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFlashCardsAtIndex(model, INDEX_FIRST_FLASHCARD);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
