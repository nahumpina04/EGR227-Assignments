// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class HtmlValidatorTest {
    /**
     * Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                            validatorOutputToString(validator));
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test1.html and expected_output/validate_result_for_test1.txt
     */
	@Test
	public void test1(){
        //<b> bold text <i> bold and italic </i> just bold </b>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("i", true));      // <i>
        tags.add(new HtmlTag("i", false));     // </i>
        tags.add(new HtmlTag("b", false));     // </b>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test1.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test2 method should test your validate() method
     * reproducing the test of
     * input_html/test2.html and expected_output/validate_result_for_test2.txt
     */
	@Test
	public void test2(){
        //<html><b> bold text <i> italic text </i> oops, forgot to close the rest
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html", true));      // <html>
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("i", true));     // <i>
        tags.add(new HtmlTag("i", false));     // </i>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test2.txt"),
                validatorOutputToString(validator));
	}


    /**
     * This test3 method should test your validate() method
     * reproducing the test of
     * input_html/test3.html and expected_output/validate_result_for_test3.txt
     */
	@Test
	public void test3(){
        //<b> bold text <i> bold and italic </b> oops, closed bold first </i>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("i", true));      // <i>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("i", false));     // </i>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test3.txt"),
                validatorOutputToString(validator));
	}


    /**
     * This test4 method should test your validate() method
     * reproducing the test of
     * input_html/test4.html and expected_output/validate_result_for_test4.txt
     */
	@Test
	public void test4(){
        //<b> some text <i> </b> </i> </b> </html>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("i", true));      // <i>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("i", false));     // </i>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("html", false));     // </html>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test4.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test5 method should test your validate() method
     * reproducing the test of
     * input_html/test5.html and expected_output/validate_result_for_test5.txt
     */
	@Test
	public void test5(){
        //</html>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html", false));     // </html>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test5.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test6.html and expected_output/validate_result_for_test6.txt
     */
	@Test
	public void test6(){
        //This file intentionally has no tags in it.
        Queue<HtmlTag> tags = new LinkedList<>();
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test6.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test7 method should test your validate() method
     * reproducing the test of
     * input_html/test7.html and expected_output/validate_result_for_test7.txt
     */
	@Test
	public void test7(){
        //refer to test7.html for tags
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype", true));     // <!doctype>
        tags.add(new HtmlTag("!--", true));     // <!-- -->
        tags.add(new HtmlTag("html", true));     // <html>
        tags.add(new HtmlTag("head", true));     // <head>
        tags.add(new HtmlTag("title", true));     // <title>
        tags.add(new HtmlTag("title", false));     // </title>
        tags.add(new HtmlTag("meta", true));     // <meta>
        tags.add(new HtmlTag("link", true));     // <link>
        tags.add(new HtmlTag("head", false));     // </head>
        tags.add(new HtmlTag("body", true));     // <body>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("a", true));     // <a>
        tags.add(new HtmlTag("a", false));     // </a>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("img", true));     // <img>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("body", false));     // </body>
        tags.add(new HtmlTag("html", false));     // </html>

        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test7.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test8 method should test your validate() method
     * reproducing the test of
     * input_html/test8.html and expected_output/validate_result_for_test8.txt
     */
	@Test
	public void test8(){
        //refer to test8.html for tags
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype", true));     // <!doctype>
        tags.add(new HtmlTag("!--", true));     // <!-- -->
        tags.add(new HtmlTag("html", true));     // <html>
        tags.add(new HtmlTag("head", true));     // <head>
        tags.add(new HtmlTag("title", true));     // <title>
        tags.add(new HtmlTag("meta", true));     // <meta>
        tags.add(new HtmlTag("link", true));     // <link>
        tags.add(new HtmlTag("head", false));     // </head>
        tags.add(new HtmlTag("head", false));     // </head>
        tags.add(new HtmlTag("body", true));     // <body>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("a", true));     // <a>
        tags.add(new HtmlTag("a", false));     // </a>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("br", false));     // </br>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("img", true));     // <img>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("html", false));     // </html>

        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test8.txt"),
                validatorOutputToString(validator));
	}

	/**
	 * Tests the removeAll() method by removing three of four tags
     * from a html file
	 */
	@Test
	public void myRemoveAllTest1(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("body", true));     // <body>
        tags.add(new HtmlTag("h1", true));     // <h1>
        tags.add(new HtmlTag("h2", true));     // <h2>
        tags.add(new HtmlTag("h4", true));     // <h4>
        tags.add(new HtmlTag("h4", false));     // </h4>
        tags.add(new HtmlTag("h2", false));     // </h2>
        tags.add(new HtmlTag("h1", false));     // </h1>
        tags.add(new HtmlTag("body", false));     // </body>

        HtmlValidator vTags = new HtmlValidator(tags);
        //removed tags
        vTags.removeAll("h1");
        vTags.removeAll("h2");
        vTags.removeAll("h4");

        Queue<HtmlTag> expectedTags = new LinkedList<HtmlTag>();
        //expected tags
        expectedTags.add(new HtmlTag("body", true));     // </h1>
        expectedTags.add(new HtmlTag("body", false));     // </h1>

        Assert.assertEquals(expectedTags, vTags.getTags());

	}

	/**
	 * Tests the removeAll() method by removing all tags that are self-closing
	 */
	@Test
	public void myRemoveAllTest2(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype", true));     // <!doctype>
        tags.add(new HtmlTag("!--", true));     // <!-- -->
        tags.add(new HtmlTag("html", true));     // <html>
        tags.add(new HtmlTag("head", true));     // head>
        tags.add(new HtmlTag("title", true));     // <title>
        tags.add(new HtmlTag("title", false));     // </title>
        tags.add(new HtmlTag("meta", true));     // <meta>
        tags.add(new HtmlTag("link", true));     // <link>
        tags.add(new HtmlTag("head", false));     // </head>
        tags.add(new HtmlTag("body", true));     // <body>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("a", true));     // <a>
        tags.add(new HtmlTag("a", false));     // </a>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("img", true));     // <img>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("body", false));     // </body>
        tags.add(new HtmlTag("html", false));     // </html>

        HtmlValidator vTags = new HtmlValidator(tags);

        Queue<HtmlTag> expectedTags = new LinkedList<HtmlTag>();
        expectedTags.add(new HtmlTag("html", true));     // <html>
        expectedTags.add(new HtmlTag("head", true));     // <head>
        expectedTags.add(new HtmlTag("title", true));     // <title>
        expectedTags.add(new HtmlTag("title", false));     // </title>
        expectedTags.add(new HtmlTag("head", false));     // </head>
        expectedTags.add(new HtmlTag("body", true));     // <body>
        expectedTags.add(new HtmlTag("p", true));     // <p>
        expectedTags.add(new HtmlTag("a", true));     // <a>
        expectedTags.add(new HtmlTag("a", false));     // </a>
        expectedTags.add(new HtmlTag("p", false));     // </p>
        expectedTags.add(new HtmlTag("p", true));     // <p>
        expectedTags.add(new HtmlTag("p", false));     // </p>
        expectedTags.add(new HtmlTag("body", false));     // </body>
        expectedTags.add(new HtmlTag("html", false));     // </html>

        //iterates and checks if the tag is self-closing using isSelfClosing()
        //if true, the tag is removed from the list
        for(int i = vTags.getTags().size(); i > 0; i--){
            HtmlTag currentTag = tags.remove();
            if(currentTag.isSelfClosing())
                vTags.removeAll(currentTag.getElement());
            else
                tags.add(currentTag);
        }

        Assert.assertEquals(expectedTags, vTags.getTags());
	}

	/**
	 * Tests the removeAll() method by removing all list items and headers
	 */
	@Test
	public void myRemoveAllTest3(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html", true));     // <html>
        tags.add(new HtmlTag("body", true));     // <body>
        tags.add(new HtmlTag("h2", true));     // <h2>
        tags.add(new HtmlTag("h2", false));     // </h2>
        tags.add(new HtmlTag("ul", true));     // <ul>
        tags.add(new HtmlTag("li", true));     // <li>
        tags.add(new HtmlTag("li", false));     // </li>
        tags.add(new HtmlTag("li", true));     // <li>
        tags.add(new HtmlTag("li", false));     // </li>
        tags.add(new HtmlTag("li", true));     // <li>
        tags.add(new HtmlTag("li", false));     // </li>
        tags.add(new HtmlTag("ul", false));     // </ul>
        tags.add(new HtmlTag("html", false));     // </html>
        tags.add(new HtmlTag("body", false));     // </body>

        HtmlValidator vTags = new HtmlValidator(tags);
        //removed tags
        vTags.removeAll("h2");
        vTags.removeAll("ul");
        vTags.removeAll("li");

        Queue<HtmlTag> expectedTags = new LinkedList<HtmlTag>();
        //expected tags
        expectedTags.add(new HtmlTag("html", true));     // <html>
        expectedTags.add(new HtmlTag("body", true));     // <body>
        expectedTags.add(new HtmlTag("html", false));     // </html>
        expectedTags.add(new HtmlTag("body", false));     // </body

        Assert.assertEquals(expectedTags, vTags.getTags());

    }

    /**
     * Tests the removeAll() method by removing all tags from a sample
     */
    @Test
    public void myRemoveAllTest4(){
        //gets tags from test7.html
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype", true));     // <!doctype>
        tags.add(new HtmlTag("!--", true));     // <!-- -->
        tags.add(new HtmlTag("html", true));     // <html>
        tags.add(new HtmlTag("head", true));     // <head>
        tags.add(new HtmlTag("title", true));     // <title>
        tags.add(new HtmlTag("title", false));     // </title>
        tags.add(new HtmlTag("meta", true));     // <meta>
        tags.add(new HtmlTag("link", true));     // <link>
        tags.add(new HtmlTag("head", false));     // </head>
        tags.add(new HtmlTag("body", true));     // <body>
        tags.add(new HtmlTag("p", true));     // <p>
        tags.add(new HtmlTag("a", true));     // <a>
        tags.add(new HtmlTag("a", false));     // </a>
        tags.add(new HtmlTag("p", false));     // </p>
        tags.add(new HtmlTag("img", true));     // <img>
        tags.add(new HtmlTag("body", false));     // </body>
        tags.add(new HtmlTag("html", false));     // </html>

        HtmlValidator vTags = new HtmlValidator(tags);

        HtmlTag currentTag = tags.remove();
        //iterations to remove all tags
        for(int i = vTags.getTags().size(); i > 0; i--){
            if(!currentTag.isSelfClosing()) {
                vTags.removeAll(currentTag.getElement());
                i--;
            } else {
                vTags.removeAll(currentTag.getElement());
            }
            currentTag = tags.remove();
        }

        Queue<HtmlTag> expectedTags = new LinkedList<HtmlTag>();
        //no tags are added since all are expected to be removed

        Assert.assertEquals(expectedTags, vTags.getTags());
    }

    /*
    * Tests to ensure that the IllegalArgumentException is thrown when null is called
    */
    @Test(expected = IllegalArgumentException.class)
    public void myRemoveAllTestNull(){
        //only add one tag to the queue
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("img", true));     // <img>
        HtmlValidator vTags = new HtmlValidator(tags);

        vTags.removeAll(null);
    }

    @Test
    public void addTagTest(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("img", true));     // <img>
        HtmlValidator vTags = new HtmlValidator(tags);
        vTags.addTag(new HtmlTag("link", true));

        Queue<HtmlTag> expectedTags = new LinkedList<HtmlTag>();
        expectedTags.add(new HtmlTag("img", true));
        expectedTags.add(new HtmlTag("link", true));

        Assert.assertEquals(expectedTags, vTags.getTags());
    }
    @Test(expected = IllegalArgumentException.class)
    public void addTagTestNull(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("img", true));     // <img>
        HtmlValidator vTags = new HtmlValidator(tags);

        vTags.addTag(null);
    }

    @Test
    public void getTagsTest(){
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("img", true));     // <img>
        HtmlValidator vTags = new HtmlValidator(tags);
        Queue<HtmlTag> expectedTags = new LinkedList<HtmlTag>();
        expectedTags.add(new HtmlTag("img", true));

        Assert.assertEquals(expectedTags, vTags.getTags());
    }
 }
