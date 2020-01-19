package test.contract.utils;


import java.time.LocalDate;

public class StringHelpler {

    /**
     * из "ФаМИлиИи", "имЯ" и "оТЧЕСтвА" делает "Фимилия Имя Отчество"
     *
     * @param first    Имя
     * @param second   оТчестВО
     * @param last     фАМилиЯ
     * @param initials формат Фатвомилия И.О.
     *      * @return Фамилия Имя Отчес
     */
    public static String formatFIO(String first, String second, String last, boolean initials)
    {
        String ln = ( last != null && !"".equals( last ) ? new StringBuilder().append(
                last.substring( 0, 1 ).toUpperCase() ).append(
                last.substring(
                        1 ).toLowerCase() ).append( " " ).toString() : "" );
        if( ln.contains( "-" ) )
        {
            String tmp[] = ln.split( "-" );
            ln = tmp[ 0 ];
            for( int i = 1; i < tmp.length; i++ )
            {
                String s = tmp[ i ];
                if( s.length() > 0 )
                {
                    s = new StringBuilder().append( s.substring( 0, 1 ).toUpperCase() ).append( s.substring(
                            1 ).toLowerCase() ).toString();
                }
                ln = new StringBuilder( ln ).append( "-" ).append( s ).toString();
            }
        }
        String fn = first == null || "".equals( first ) ? "" : ( initials ? first.substring( 0,
                1 ).toUpperCase() + "." :
                first.substring( 0, 1 ).toUpperCase() + first.substring( 1 ).toLowerCase() + " " );
        String sn = second == null || "".equals( second ) ? "" : ( initials ? second.substring( 0,
                1 ).toUpperCase() + "." :
                second.substring( 0, 1 ).toUpperCase() + second.substring( 1 ).toLowerCase() );
        return ln + fn + sn;
    }

    public static String formatFIO(String first, String second, String last){
        return formatFIO(first, second, last,false);
    }

    public static String formatSnils(String snils)
    {
        if (snils!=null&&snils.length()==11)
        {
            return snils.substring(0,3)+"-"+snils.substring(3,6)+"-"+snils.substring(6,9)+" "+snils.substring(9,snils.length());
        }
        else
            if (snils !=null)
            {
                return snils;
            }
            else
                return "";
    }

    public static String formatAddress(String postIndex,String city,String street,String house,String building,String flat,String room){
        String address = "";
        if(postIndex!=null){
            address+=postIndex.trim()+" ";
        }
        if(city!=null){
            String[] split = city.split("[.]");
            String shortName = "";
            String name = "";
            if(split.length > 1){
                shortName = split[0].toLowerCase() + ". ";
                name = split[1].substring(0, 1) + split[1].substring(1, split[1].length()).toLowerCase();
            }else{
                name = split[0].substring(0, 1) + split[0].substring(1, split[0].length()).toLowerCase();
            }
            address+=shortName + name.trim()+" ";
        }
        if(street!=null&&!street.trim().isEmpty()){
            address+=street.substring(0, 1)+street.substring(1, street.length()).toLowerCase().trim()+" ";
        }
        if(house!=null&&!house.trim().isEmpty()){
            address+="д. "+house.trim()+" ";
        }
        if(building!=null&&!building.trim().isEmpty()){
            address=address.substring(0,address.length()-1)+"/"+building.trim()+" ";
        }
        if(flat!=null&&!flat.equals("0")){
            address+="кв. "+flat+" ";
        }
        if(room!=null&&!room.trim().isEmpty()){
            address+=room.trim()+" ";
        }
        return address;
    }

    public static String formatOwnersEquity (Long partNumerator, Long partDenominator)
    {
        StringBuilder ownersEquity = new StringBuilder();
        if (partNumerator!=null && partNumerator!=0)
        {
            ownersEquity.append(partNumerator);
        }
        else
        {
            ownersEquity.append("-");
        }
        ownersEquity.append("/");
        if (partDenominator!=null && partDenominator!=0)
        {
            ownersEquity.append(partDenominator);
        }
        else
        {
            ownersEquity.append("-");
        }
        return ownersEquity.toString();
    }

    public static String formatDocumentShort(String docSeria, String docNumber){
        return formatDocument(docSeria,docNumber,null,null,null);
    }


    public static String formatDocument(String docSeria, String docNumber, LocalDate givenWhen,String givenBy,String typeDoc){
        String format = "";
        if(typeDoc!=null){
            format+=typeDoc+" ";
        }
        if(givenWhen!=null){
            format+="выдан : "+DateHelpler.localDateToString(givenWhen)+" ";
        }
        if(givenBy!=null){
            format+=givenBy+" \n";
        }
        if(docSeria!=null&&docNumber!=null){
            format+=docSeria+" "+docNumber;
        }
        return format;
    }

}
