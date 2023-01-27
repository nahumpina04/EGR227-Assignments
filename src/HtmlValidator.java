/**
 * Class: HtmlValidator
 * Author: Nahum Pina
 * Started: 1/15/23
 * Last Updated: 1/25/23
 * Purpose:
 * - Take a queue of HtmlTags and print them out as structured within a html file
 * - Manage the tags as with any list (add, remove, return)
 */

import java.util.*;
public class HtmlValidator {

    //class constant defining the tab size (four spaces)
    private static final String TAB = "    ";
    //queue that holds the tags
    private Queue<HtmlTag> tags;

    //default constructor
    public HtmlValidator(){
        this.tags = new LinkedList<HtmlTag>();
    }

    //constructor with queue
    public HtmlValidator(Queue<HtmlTag> tags) throws IllegalArgumentException{
        //checks if element is null; throws IllegalArgumentException if is
        if(tags == null)
            throw new IllegalArgumentException("Tags cannot be null!");
        this.tags = tags;
    }

    //adds a tag to the queue
    public void addTag(HtmlTag tag) throws IllegalArgumentException{
        //checks if element is null; throws IllegalArgumentException if is
        if(tag == null)
            throw new IllegalArgumentException("Cannot add a null tag!");
        tags.add(tag);
    }

    //returns the tags as a queue
    public Queue<HtmlTag> getTags(){
        return tags;
    }

    //removes all tags containing the element specified by the parameter
    public void removeAll(String element) throws IllegalArgumentException{
        //checks if element is null; throws IllegalArgumentException if is
        if(element == null)
            throw new IllegalArgumentException("Cannot be a null element!");
       // Queue<HtmlTag> newTags = new LinkedList<HtmlTag>();
        //iterates through the tags to check for the removed element
        for(int i = tags.size(); i > 0; i--){
            HtmlTag currentTag = tags.remove();
            if(!currentTag.toString().contains(element)) {
                tags.add(currentTag);
            }
        }
    }

    public void validate(){
        Stack<HtmlTag> tagStack = new Stack<HtmlTag>();
        for(int i = 0; i < tags.size(); i++){
            HtmlTag currentTag = tags.remove();
            tags.add(currentTag);
            if(currentTag.isSelfClosing() && !currentTag.toString().equals("</br>")) {
                printWithTabs(currentTag, tagStack.size());
            } else if(currentTag.isOpenTag()) {
                printWithTabs(currentTag, tagStack.size());
                tagStack.push(currentTag);
            } else if(!tagStack.isEmpty() && currentTag.matches(tagStack.peek())){
                tagStack.pop();
                printWithTabs(currentTag, tagStack.size());
            } else {
                System.out.println("ERROR unexpected tag: " + currentTag);
            }
        }
        //reports all unclosed tags as errors
        while(!tagStack.isEmpty()){
            HtmlTag tag = tagStack.pop();
            System.out.println("ERROR unclosed tag: " + tag);
        }
    }

    //helper method designed to print the tags with the appropriate indentations
    //(characterized by the size of the stack at the call)
    public void printWithTabs(HtmlTag tag, int tabNum){
        String line = "";
        for(int i = 0; i < tabNum; i++){
            line += TAB;
        }
        System.out.println(line + tag.toString());
    }
}