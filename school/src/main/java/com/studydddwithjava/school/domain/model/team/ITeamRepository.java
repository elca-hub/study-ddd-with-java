package com.studydddwithjava.school.domain.model.team;

import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeamRepository {
    /**
     * チームをDBに保存
     * @param teacher 保存する教員モデル
     * @param team 保存対象のチームモデル
     */
    void save(Teacher teacher, Team team);

    /**
     * 教員モデルが保有しているチームモデルを検索
     * @param teacher 検索対象の教員モデル
     * @return 検索結果のリスト
     */
    List<Team> findByTeacher(Teacher teacher);

    /**
     * チームidに対応するチームを検索
     * @param id チームid
     * @return 対応するチームモデル
     */
    Optional<Team> findById(String id);

    /**
     * 生徒モデルが保有しているチームidを全て削除
     * @param student 脱退対象の生徒モデル
     */

    void removeStudent(Student student);

    /**
     * チームモデルをDBから削除
     * @param team 削除対象のチームモデル
     */
    void delete(Team team);

    /**
     * 生徒が所属しているチームモデルを検索
     * @param student 検索対象の生徒モデル
     * @return 生徒が所属しているチームモデルのリスト
     */
    List<Team> findByStudent(Student student);
}
