package com.example.studentmanagementapplicationn.comparators;

import com.example.studentmanagementapplicationn.entity.university.Course;

public class CustomComparator {

    public static int compareCourses(Course o1, Course o2) {
        return (-1) * o1.getName().compareTo(o2.getName());
    }
}
