package com.studydddwithjava.school.domain.model.teacher;

import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.user.UserName;

import java.util.List;
import java.util.Optional;

public interface ITeacherRepository {

    /**
     * 教員のユーザ名とパスワードから教員を検索
     * @param teacher 検索対象の教員モデル
     * @return 検索結果
     */
    Teacher findByUserNameAndPw(Teacher teacher);

    /**
     * 教員のユーザ名から教員モデルを検索
     * @param username 検索対象の教員名
     * @return 検索結果
     */
    Optional<Teacher> findByUserName(UserName username);

    /**
     * 対象の教員モデルをDBに保存
     * @param teacher 保存対象の教員モデル
     */
    void save(Teacher teacher);

    /**
     * 対象の教員モデルをDBから削除
     * @param teacher 削除対象の教員モデル
     */
    void delete(Teacher teacher);

    /**
     * 対象の教員モデル内にあるidに対応しているDBの情報を更新
     * @param teacher 更新対象の教員モデル
     */
    void update(Teacher teacher);

    /**
     * 教員モデルが保有している生徒モデルをDBから取得
     * @param teacher 取得対象の教員モデル
     * @return 教員モデルによって登録された生徒モデルのリスト
     */

    List<Student> fetchStudent(Teacher teacher);
}
