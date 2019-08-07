select fa.facId, fa."name", cl.classNumber, st.lastName, st.firstName from faculty as fa
inner join "class" as cl on cl.facId = fa.facId
inner join enroll as en on en.classNumber = cl.classNumber
inner join student as st on st.stuId = en.stuId
order by cl.classNumber, st.lastName;