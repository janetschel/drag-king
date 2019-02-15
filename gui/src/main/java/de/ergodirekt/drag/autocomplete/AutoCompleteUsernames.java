package de.ergodirekt.drag.autocomplete;

import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * An example on how to use the {@link AutoCompleteDocument}.
 *
 * @see AutoCompleteDocument
 * @see CompletionService
 *
 * @author Samuel Sjoberg, http://samuelsjoberg.com
 * @version 1.0.0
 */
public class AutoCompleteUsernames implements Runnable {
    private String[] usernames;
    private JTextField input;

    public AutoCompleteUsernames(String[] usernames) {
        this.usernames = usernames;
    }

    /**
     * Create the GUI and display it to the world.
     */
    public void run() {
        System.out.println(Arrays.toString(usernames));
        // Create the completion service.
        NameService nameService = new NameService(usernames);

        // Create the input field.
        input = new JTextField(30);

        // Create the auto completing document model with a reference to the
        // service and the input field.
        Document autoCompleteDocument = new AutoCompleteDocument(nameService,
                input);

        // Set the auto completing document as the document model on our input
        // field.
        input.setDocument(autoCompleteDocument);
    }

    public JTextField getInput() {
        return input;
    }

    /**
     * A simple {@link CompletionService} providing completion for most popular
     * baby names 2008.
     */
    private static class NameService implements CompletionService<String> {

        /** Our name data. */
        private String[] data;

        /**
         * Create a new <code>NameService</code> and populate it.
         */
        NameService(String[] data) {
            this.data = data;
        }

        /** {@inheritDoc} */
        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            for (String o : data) {
                b.append(o).append("\n");
            }
            return b.toString();
        }

        /** {@inheritDoc} */
        public String autoComplete(String startsWith) {
            // Naive implementation, but good enough for the sample
            String hit = null;
            for (String o : data) {
                if (o.startsWith(startsWith)) {
                    // CompletionService contract states that we only
                    // should return completion for unique hits.
                    if (hit == null) {
                        hit = o;
                    } else {
                        hit = null;
                        break;
                    }
                }
            }
            return hit;
        }
    }
}
