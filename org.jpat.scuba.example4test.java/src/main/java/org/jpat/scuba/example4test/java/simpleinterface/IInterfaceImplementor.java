package org.jpat.scuba.example4test.java.simpleinterface;

public class IInterfaceImplementor implements IInterface
{
    private final String techVal;

    public IInterfaceImplementor(final String techVal)
    {
        super();
        this.techVal = techVal;
    }

    @Override
    public void yourInterface()
    {
        final StringBuilder strBldr = new StringBuilder();

        strBldr.append("considered more efficient than ");
        strBldr.append("contatinating strings in a classical way");

        strBldr.append("\nThis is my technical value: ");

        strBldr.append(this.techVal);

        strBldr.trimToSize();

        System.out.println(strBldr.toString());
    }
}
