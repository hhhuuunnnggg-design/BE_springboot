package com.example.demo.Repository.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Employee;
import com.example.demo.Repository.ConnectionUtil;
import com.example.demo.Repository.IstudentRepository;

@Repository
public class StudentRepository implements IstudentRepository {

    public Employee saveOrUpdate(Employee employee, Integer idFromParam) {
        Transaction transaction = null;

        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Ưu tiên ID từ param nếu có, ghi đè ID trong đối tượng employee
            if (idFromParam != null) {
                employee.setId(idFromParam);
            }

            if (employee.getId() != null) { // Nếu ID khác null, thực hiện cập nhật
                Employee existingEmployee = findById(employee.getId());
                if (existingEmployee != null) {
                    // Cập nhật các trường cho Employee hiện có
                    existingEmployee.setName(employee.getName());
                    existingEmployee.setCode(employee.getCode());
                    existingEmployee.setNumberPhone(employee.getNumberPhone());
                    existingEmployee.setBirthDate(employee.getBirthDate());
                    existingEmployee.setGender(employee.getGender());
                    existingEmployee.setSalary(employee.getSalary());
                    existingEmployee.setTest(employee.getTest());

                    // Sử dụng merge để tránh lỗi NonUniqueObjectException
                    session.merge(existingEmployee);
                } else {
                    return null;
                }
            } else { // Nếu ID null, thực hiện thêm mới
                session.save(employee);
            }

            transaction.commit(); // Commit giao dịch nếu không có lỗi
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback nếu có lỗi
            }
            throw new RuntimeException("Failed to save or update employee: " + e.getMessage(), e);
        }

        return employee; // Trả về đối tượng đã được lưu hoặc cập nhật
    }

    @Override
    public List<Employee> findAll() {
        Session session = ConnectionUtil.sessionFactory.openSession(); // Bước 1: Mở phiên làm việc (Session) từ
                                                                       // ConnectionUtil
        List<Employee> students = null;
        try {
            students = session.createQuery("FROM Employee").getResultList(); // Bước 2: Sử dụng HQL để lấy danh sách
                                                                             // sinh
                                                                             // viên
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Bước 3: Đóng phiên làm việc sau khi lấy danh sách xong
        }
        return students;
    }

    @Override
    public Employee findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Employee employee = null;
        try {
            // Sử dụng HQL (Hibernate Query Language) để tìm Employee theo ID
            Query<Employee> query = session.createQuery("FROM Employee WHERE id = :id", Employee.class);
            query.setParameter("id", id); // Truyền ID vào truy vấn
            employee = query.uniqueResult(); // Trả về đối tượng Employee duy nhất
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Đóng phiên làm việc sau khi tìm kiếm xong
        }
        return employee;
    }

    @Override
    public Employee save(Employee student) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {

                session.saveOrUpdate(student);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback(); // Rollback nếu có lỗi
                }
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    @Override
    public Employee updateStudentById(int id, Employee updatedEmployee) {
        Transaction transaction = null; // Khởi tạo biến để theo dõi giao dịch
        Employee existingEmployee = findById(id);
        if (existingEmployee == null) {
            return null;
        }
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {

            transaction = session.beginTransaction(); // Bắt đầu giao dịch mới
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setCode(updatedEmployee.getCode());
            existingEmployee.setNumberPhone(updatedEmployee.getNumberPhone());
            existingEmployee.setBirthDate(updatedEmployee.getBirthDate());
            existingEmployee.setGender(updatedEmployee.getGender());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setTest(updatedEmployee.getTest());

            session.update(existingEmployee);
            transaction.commit(); // Commit giao dịch nếu không có lỗi
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update employee: " + e.getMessage(), e);

        }
        return existingEmployee;
    }

    @Override
    public void deleteStudentById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Employee s WHERE s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
    }

    @Override
    public List<Employee> searchStudents(String name, String phone, String startDate, String endDate, String gender,
            String tienluong) {
        List<Employee> employees = null;
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("FROM Employee e WHERE 1=1 ");

            if (name != null && !name.isEmpty()) {
                hql.append("AND e.name LIKE :name ");
            }
            if (phone != null && !phone.isEmpty()) {
                hql.append("AND e.numberPhone LIKE :phone ");
            }
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                hql.append("AND e.birthDate BETWEEN :startDate AND :endDate ");
            }
            if (gender != null && !gender.isEmpty()) {
                hql.append("AND e.gender = :gender ");
            }
            if (tienluong != null && !tienluong.isEmpty()) {
                hql.append("AND e.salary = :tienluong ");
            }

            Query<Employee> query = session.createQuery(hql.toString(), Employee.class);

            if (name != null && !name.isEmpty()) {
                query.setParameter("name", "%" + name + "%");
            }
            if (phone != null && !phone.isEmpty()) {
                query.setParameter("phone", "%" + phone + "%");
            }
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.setParameter("startDate", LocalDate.parse(startDate)); // Parse khi không rỗng
                query.setParameter("endDate", LocalDate.parse(endDate));
            }
            if (gender != null && !gender.isEmpty()) {
                query.setParameter("gender", gender);
            }
            if (tienluong != null && !tienluong.isEmpty()) {
                query.setParameter("tienluong", tienluong);
            }

            employees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

}