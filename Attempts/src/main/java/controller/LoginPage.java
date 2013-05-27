package controller;


import org.apache.wicket.authroles.authentication.panel.SignInPanel;

public class LoginPage extends BasePage
{
  public LoginPage()
  {
    add(new SignInPanel("signInPanel"));
  }
}
