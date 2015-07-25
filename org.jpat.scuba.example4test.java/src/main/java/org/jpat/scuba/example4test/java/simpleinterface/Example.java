package org.jpat.scuba.example4test.java.simpleinterface;

public final class Example
{
    public Example()
    {
        super();
    }

    public static void main(final String args[])
    {
        String techVal = null;
        if (null == args || 0 == args.length)
        {
            techVal = " User has not provided a tech value.";
        }
        else
        {
            techVal = args[0];
        }
        final IInterface iinterfaceWillbeInvoked = new IInterfaceImplementor(techVal);
        iinterfaceWillbeInvoked.yourInterface();
    }
}
