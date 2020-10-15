package seedu.forgetfulnus.logic.commands;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class NextCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new UserPrefs());
    }

    @Test
    public void execute_quizModeReminderCheck() {
        assertCommandSuccess(new NextCommand(), model, NextCommand.QUIZMODE_REMINDER, expectedModel);
    }

    @Test
    public void execute_indexCheck() throws CommandException {
        new QuizCommand().executeWithChecks(model);
        Assertions.assertEquals(model.getQuizModeIndex(), 0);

        try {
            model.updateQuizModeIndex(-1);
            new NextCommand().executeWithChecks(model);
        } catch (CommandException e) {
            Assertions.assertEquals(e.getMessage(), Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        model.updateQuizModeIndex(0);
        try {
            int i = model.getFilteredFlashCardList().size();
            while (i > 1) {
                new NextCommand().executeWithChecks(model);
                i--;
            }
            System.out.println("hng: " + model.getFilteredFlashCardList().size());
            Assertions.assertEquals(model.getQuizModeIndex() + 1, model.getFilteredFlashCardList().size());

            new NextCommand().executeWithChecks(model);
            Assertions.assertEquals(model.getQuizModeIndex(), 0);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
