package com.example.lifescribev01;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.lifescribev01.database.AppDatabase;
import com.example.lifescribev01.database.Person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.List;

public class FamilyTreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_tree);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final AppDatabase appDb = MainActivity.GetDatabase();

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");

        List<Person> targetParents = appDb.parentXRefDao().getParentsOfPerson(id);
        List<Person> targetChildren = appDb.parentXRefDao().getChildrenOfPerson(id);
        List<Person> targetSiblings = appDb.siblingxRefDao().getSiblingOfPerson(id);
        Person targetSpouse = appDb.personDao().findByID(appDb.personDao().findByID(id).spouseID);
        Person target = appDb.personDao().findByID(id);
        Button Parent1 = findViewById(R.id.FamilyTreePerson);
        Button Parent2 = findViewById(R.id.FamilyTreePerson2);
        Button Sibling1 = findViewById(R.id.FamilyTreePerson3);
        Button Sibling2 = findViewById(R.id.FamilyTreePerson4);
        Button Sibling3 = findViewById(R.id.FamilyTreePerson5);
        Button CenterPerson = findViewById(R.id.FamilyTreePerson6);
        Button Spouse= findViewById(R.id.FamilyTreePerson7);
        Button Child1 = findViewById(R.id.FamilyTreePerson8);
        Button Child2 = findViewById(R.id.FamilyTreePerson9);
        Button Child3 = findViewById(R.id.FamilyTreePerson10);
        Button Child4 = findViewById(R.id.FamilyTreePerson11);
        View ChildLine1 = findViewById(R.id.child_1);
        View ChildLine2 = findViewById(R.id.child_2);
        View ChildLine3 = findViewById(R.id.child_3);
        View ChildLine4 = findViewById(R.id.child_4);
        View ChildAcross = findViewById(R.id.spouse_child_across);
        View ChildDown = findViewById(R.id.spouse_children_down);
        View ParentAcross = findViewById(R.id.parent_line);
        View ParentDown = findViewById(R.id.parent_sibling_down);
        View SiblingLine1 = findViewById(R.id.parent_sibling_1);
        View SiblingLine2 = findViewById(R.id.parent_sibling_2);
        View SiblingLine3 = findViewById(R.id.parent_sibling_3);
        View SiblingAcross = findViewById(R.id.parent_sibling_across);
        View SiblingDown = findViewById(R.id.parent_self);
        View SpouseAcross = findViewById(R.id.spouse_line);

        CenterPerson.setText(target.name);
        if(targetSpouse!=null){
            Spouse.setText(targetSpouse.name);
        }
        else{
            Spouse.setVisibility(View.INVISIBLE);
        }
        if(targetParents.size()>=1 && targetParents.get(0)!=null){
            Parent1.setText(targetParents.get(0).name);
        }
        else{
            ParentAcross.setVisibility(View.INVISIBLE);
            ParentDown.setVisibility(View.INVISIBLE);
            Parent1.setVisibility(View.INVISIBLE);
        }
        if(targetParents.size()==2 && targetParents.get(1)!=null){
            Parent2.setText(targetParents.get(1).name);
        }
        else{
            Parent2.setVisibility(View.INVISIBLE);
        }
        if(targetSiblings.size()>=1 && targetSiblings.get(0)!=null){
            Sibling1.setText(targetSiblings.get(0).name);
        }
        else{
            Sibling1.setVisibility(View.INVISIBLE);
            SiblingLine1.setVisibility(View.INVISIBLE);
        }
        if(targetSiblings.size()>=2 && targetSiblings.get(1)!=null){
            Sibling2.setText(targetSiblings.get(1).name);
        }
        else{
            Sibling2.setVisibility(View.INVISIBLE);
            SiblingLine2.setVisibility(View.INVISIBLE);
        }
        if(targetSiblings.size()>=3 && targetSiblings.get(2)!=null){
            Sibling3.setText(targetSiblings.get(2).name);
        }
        else{
            Sibling3.setVisibility(View.INVISIBLE);
            SiblingLine3.setVisibility(View.INVISIBLE);
        }
        if(targetChildren.size()>=1 && targetChildren.get(0)!=null){
            Child1.setText(targetChildren.get(0).name);
        }
        else{
            Child1.setVisibility(View.INVISIBLE);
            ChildLine1.setVisibility(View.INVISIBLE);
            ChildAcross.setVisibility(View.INVISIBLE);
            ChildDown.setVisibility(View.INVISIBLE);

        }
        if(targetChildren.size()>=2 && targetChildren.get(1)!=null){
            Child2.setText(targetChildren.get(1).name);
        }
        else{
            Child2.setVisibility(View.INVISIBLE);
            ChildLine2.setVisibility(View.INVISIBLE);
        }
        if(targetChildren.size()>=3 && targetChildren.get(2)!=null){
            Child3.setText(targetChildren.get(2).name);
        }
        else{
            Child3.setVisibility(View.INVISIBLE);
            ChildLine3.setVisibility(View.INVISIBLE);
        }
        if(targetChildren.size()>=4 && targetChildren.get(3)!=null){
            Child4.setText(targetChildren.get(3).name);
        }
        else{
            Child4.setVisibility(View.INVISIBLE);
            ChildLine4.setVisibility(View.INVISIBLE);
        }
        if(targetParents.size() < 1 && targetSiblings.size() < 1){
            SiblingAcross.setVisibility(View.INVISIBLE);
            SiblingDown.setVisibility(View.INVISIBLE);
        }
        if(targetChildren.size() < 1 && targetSpouse == null){
            SpouseAcross.setVisibility(View.INVISIBLE);
        }




    }



}
