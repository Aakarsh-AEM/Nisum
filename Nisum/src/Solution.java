
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution
{   
    public static void main (String[] args) throws java.lang.Exception
    {
        ArrayList<ZipRange> x = new ArrayList<>();

        x.add(new ZipRange(94133,94133));
        x.add(new ZipRange(94200,94299));
        x.add(new ZipRange(94600,94699));
        x.add(new ZipRange(94226,94399));

        x = merge(x);

        for(ZipRange i : x)
        {
            System.out.println(i.getStart() + " " + i.getEnd());
        }
    }

    public static ArrayList<ZipRange> merge(ArrayList<ZipRange> ZipRanges) {

        if(ZipRanges.size() == 0 || ZipRanges.size() == 1)
            return ZipRanges;

        Collections.sort(ZipRanges, new ZipRangeComparator());

        ZipRange first = ZipRanges.get(0);
        int start = first.getStart();
        int end = first.getEnd();

        ArrayList<ZipRange> result = new ArrayList<ZipRange>();

        for (int i = 1; i < ZipRanges.size(); i++) {
            ZipRange current = ZipRanges.get(i);
            if (current.getStart() <= end) {
                end = Math.max(current.getEnd(), end);
            } else {
                result.add(new ZipRange(start, end));
                start = current.getStart();
                end = current.getEnd();
            }
        }

        result.add(new ZipRange(start, end));
        return result;
    }
}

class ZipRange 
{
    private int start;
    private int end;

    ZipRange() {
        start = 0;
        end = 0;
    }

    ZipRange(int s, int e) 
    {
        start = s;
        end = e;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

class ZipRangeComparator implements Comparator<ZipRange>
{
    public int compare(ZipRange i1, ZipRange i2)
    {
        return i1.getStart() - i2.getStart();
    }
}