package msd.com.vaccinebook;

import java.util.ArrayList;
import java.util.List;


public class Group
{
    public String string;
    public final List<String> childrenItems= new ArrayList<String>();

    public Group(String string)
    {
        this.string = string;
    }
}
