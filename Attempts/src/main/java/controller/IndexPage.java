package controller;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 8/11/11
 * Time: 18:20
 *
 */

@AuthorizeInstantiation("ROLE_crowd-administrator")
public class IndexPage extends WebPage {
    public IndexPage(){
        add(new Label("message", "Hello World!"));
    }
}
