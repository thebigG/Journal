package JournalApp;

/**
 * An OptionBox is a square full of options to trigger some kind of action
 * /

/**
 *
 * @author lorenzogomez
 */
import java.util.*;
public abstract class OptionBox <E extends Option>
{
private ArrayList<Option> Options;
public OptionBox()
{
    Option x = new Option() {

        @Override
        public void DoThis() 
        {
            
        }

        @Override
        public void Do() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
}
}