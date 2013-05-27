package controller;

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class ExampleWebSession extends AuthenticatedWebSession
{
  private String userName;

  public ExampleWebSession(AuthenticatedWebApplication application,
      Request request)
  {
    super(request);
  }

  @Override
  public boolean authenticate(String userName, String password)
  {
    boolean success = userName.equals("guest") && password.equals("guest");
    
    if ( success )
      this.userName = userName;
    
    return success;
  }

  @Override
  public Roles getRoles()
  {
    Roles roles = new Roles();

    if ( isSignedIn() )
      roles.add("USER");
    
    return roles;
  }
  
  public String getUserName()
  {
    return userName;
  }
}
