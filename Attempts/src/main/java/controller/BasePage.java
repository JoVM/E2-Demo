package controller;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;

public class BasePage extends WebPage
{
  public BasePage()
  {
    super();
  }

  public BasePage(IModel model)
  {
    super(model);
  }
}
