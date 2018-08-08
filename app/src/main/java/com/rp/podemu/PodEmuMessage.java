/**

 Copyright (C) 2015, Roman P., dev.roman [at] gmail

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see http://www.gnu.org/licenses/

 */

package com.rp.podemu;


import java.util.HashMap;
import java.util.Map;

public class PodEmuMessage
{
    private String artist;
    private String album;
    private String track;
    private String external_id;
    private String genre="Unknown Genre";
    private String composer="Unknown Composer";
    private String uri="unknown uri";
    private int lengthSec;
    private int positionMS;
    private boolean isPlaying;
    private int action;
    private long timeSent;
    private int track_number;
    private int listSize;
    private int listPosition;

    private boolean enableCyrillicTransliteration=false;

    public final static int ACTION_METADATA_CHANGED=1;
    public final static int ACTION_PLAYBACK_STATE_CHANGED=2;
    public final static int ACTION_QUEUE_CHANGED=4;

    public String getArtist()  { return transliterate(artist); }
    public String getAlbum()   { return transliterate(album); }
    public String getTrackName()   { return transliterate(track); }
    public String getExternalId() { return external_id; }
    public String getGenre() { return transliterate(genre); }
    public String getComposer() { return transliterate(composer); }
    public int getLength()     { return lengthSec; }
    public boolean isPlaying() { return isPlaying; }
    public int getAction()     { return action; }
    public long getTimeSent() { return timeSent; }
    public int getPositionMS() { return positionMS; }
    public String getUri()    {        return uri;    }
    public int getTrackNumber()    {        return track_number;    }
    public int getListSize()    {   return listSize; }
    public int getListPosition()    {   return listPosition; }

    public void setArtist(String artist)   { this.artist = artist; }
    public void setAlbum(String album)     { this.album = album;   }
    public void setGenre(String genre)     { this.genre = genre;   }
    public void setComposer(String composer)     { this.composer = composer;   }
    public void setTrackName(String track)     { this.track = track;    }
    public void setExternalId(String external_id) { this.external_id = external_id;  }
    public void setLength(int length)      { this.lengthSec = length;    }
    public void setPositionMS(int positionMS)    { this.positionMS = positionMS;  }
    public void setIsPlaying(boolean isPlaying)  { this.isPlaying = isPlaying;  }
    public void setAction(int action)            { this.action = action; }
    public void setTimeSent(long timeSent) { this.timeSent = timeSent; }
    public void setUri(String uri)    {        this.uri = uri;    }
    public void setTrackNumber(int track_number)    {        this.track_number = track_number;    }
    public void setListSize(int listSize)   {   this.listSize = listSize; }
    public void setListPosition(int listPosition)   {   this.listPosition = listPosition; }

    public void setEnableCyrillicTransliteration(boolean enableTranslit) { enableCyrillicTransliteration = enableTranslit; }


    private Map<Character,String> translitMap = new HashMap<>();

    public PodEmuMessage()
    {
        translitMap.put('А',"A");
        translitMap.put('Б',"B");
        translitMap.put('В',"V");
        translitMap.put('Г',"G");
        translitMap.put('Д',"D");
        translitMap.put('Е',"E");
        translitMap.put('Ё',"JO");
        translitMap.put('Ж',"ZH");
        translitMap.put('З',"Z");
        translitMap.put('И',"I");
        translitMap.put('Й',"J");
        translitMap.put('К',"K");
        translitMap.put('Л',"L");
        translitMap.put('М',"M");
        translitMap.put('Н',"N");
        translitMap.put('О',"O");
        translitMap.put('П',"P");
        translitMap.put('Р',"R");
        translitMap.put('С',"S");
        translitMap.put('Т',"T");
        translitMap.put('У',"U");
        translitMap.put('Ф',"F");
        translitMap.put('Х',"H");
        translitMap.put('Ц',"C");
        translitMap.put('Ч',"CH");
        translitMap.put('Ш',"SH");
        translitMap.put('Щ',"SHH");
        translitMap.put('Ъ',"");
        translitMap.put('Ы',"Y");
        translitMap.put('Ь',"'");
        translitMap.put('Э',"E");
        translitMap.put('Ю',"JU");
        translitMap.put('Я',"JA");


        translitMap.put('а',"a");
        translitMap.put('б',"b");
        translitMap.put('в',"v");
        translitMap.put('г',"g");
        translitMap.put('д',"d");
        translitMap.put('е',"e");
        translitMap.put('ё',"jo");
        translitMap.put('ж',"zh");
        translitMap.put('з',"z");
        translitMap.put('и',"i");
        translitMap.put('й',"j");
        translitMap.put('к',"k");
        translitMap.put('л',"l");
        translitMap.put('м',"m");
        translitMap.put('н',"n");
        translitMap.put('о',"o");
        translitMap.put('п',"p");
        translitMap.put('р',"r");
        translitMap.put('с',"s");
        translitMap.put('т',"t");
        translitMap.put('у',"u");
        translitMap.put('ф',"f");
        translitMap.put('х',"h");
        translitMap.put('ц',"c");
        translitMap.put('ч',"ch");
        translitMap.put('ш',"sh");
        translitMap.put('щ',"shh");
        translitMap.put('ъ',"");
        translitMap.put('ы',"y");
        translitMap.put('ь',"'");
        translitMap.put('э',"e");
        translitMap.put('ю',"ju");
        translitMap.put('я',"ja");
    }

    public void bulk_update(PodEmuMessage msg)
    {
        if(msg.getAction()!=PodEmuMessage.ACTION_QUEUE_CHANGED)
        {
            this.setIsPlaying(msg.isPlaying());
            this.setTimeSent(msg.getTimeSent());
            this.setAction(msg.getAction());
            this.setLength(msg.getLength());
            this.setExternalId(msg.getExternalId());
            this.setTrackName(msg.getTrackName());
            this.setAlbum(msg.getAlbum());
            this.setGenre(msg.getGenre());
            this.setComposer(msg.getComposer());
            this.setArtist(msg.getArtist());
            this.setPositionMS(msg.getPositionMS());
            this.setUri(msg.getUri());
            this.setTrackNumber(msg.getTrackNumber());
            this.setListSize(msg.getListSize());
            this.setListPosition(msg.getListPosition());
        }
    }

    public String getLengthHumanReadable()
    {
        int length=getLength();
        int hours=getLength()/1000/60/60;
        int mins=(length-hours*60*60*1000)/1000/60;
        int secs=(length-hours*60*60*1000-mins*60*1000)/1000;

        return String.format("%02d:%02d", mins, secs);
    }


    private String transliterate(String str)
    {
        if(!enableCyrillicTransliteration || str == null)
            return str;
        else
        {
            StringBuilder transStr = new StringBuilder();
            for (int i=0; i<str.length(); i++)
            {
                transStr.append(cyrillicReplace(str.charAt(i), (i+1<str.length() && Character.isLowerCase(str.charAt(i+1))) ) );
            }
            return transStr.toString();
        }

    }

    private String cyrillicReplace(char c, boolean nextCharIsLowerCase)
    {
        String str = translitMap.get(c);
        if(str == null)
            str = String.valueOf(c);
        else if(str.length()>1 && nextCharIsLowerCase)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str.charAt(0));
            for(int i=1; i<str.length(); i++)
            {
                stringBuilder.append(Character.toLowerCase(str.charAt(i)));
            }
            str = stringBuilder.toString();
        }
        //PodEmuLog.error("PEM: translit: " + str);
        return str;
    }


}

