package com.example.project.GameTwo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

public class GametwoData implements Parcelable {

    private int[] answers;
    private int[] score_result;
    public GametwoData(){
        answers = new int[4];
        score_result = new int[4];

        for(int i =0; i<answers.length;i++)
        {
            answers[i]= -1;
            score_result[i]= 0;
        }
    }
    protected GametwoData(Parcel in) {
        answers = in.createIntArray();
        score_result = in.createIntArray();
    }
    public int getAnswer(int index)
    {
        Log.d("GametwoData","getAnswer " + index + " = " + answers[index]);
        return answers[index];
    }

    public void setAnswer(int index, int userAns)
    {
        this.answers[index] = userAns;
        Log.d("GametwoData","setAnswer " + index + " = " + answers[index]);
    }

    public int getScore_result() {
        int result=0;
        for(int i=0;i<score_result.length;i++)
        {
            result += score_result[i];
        }
        Log.d("GametwoData","getResult : " + result);
        return result;
    }
    public void setScore_result(int index, int score) {
        this.score_result[index] = score;
    }

    public boolean gameTerminated()
    {
        for(int i = 0; i<answers.length;i++)
        {
            if(answers[i]==-1)
            {
                Log.d("GametwoData","gameTerminated : getAllAnswers : index " + i + " = " + answers[i]);
                return false;
            }
        }
        return true;
    }

    public static final Creator<GametwoData> CREATOR = new Creator<GametwoData>() {
        @Override
        public GametwoData createFromParcel(Parcel in) {
            return new GametwoData(in);
        }

        @Override
        public GametwoData[] newArray(int size) {
            return new GametwoData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeIntArray(answers);
        dest.writeIntArray(score_result);
    }
}
