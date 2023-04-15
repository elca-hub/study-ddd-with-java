package com.studydddwithjava.school.domain.model.student;

import com.studydddwithjava.school.domain.model.team.Team;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    /**
     * 生徒モデルをDBに保存
     * @param student 生徒モデル
     */
    void save(Student student);

    /**
     * 同じチームに所属している生徒モデルを検索
     * @param team 検索するチームモデル
     * @return チームに所属している生徒のリスト
     */
    List<Student> findByTeamId(Team team);

    /**
     * 同じチーム内に同じ生徒番号を保有しているか否かを判定
     * @param student 比較対象の生徒モデル
     * @param team 対象となるチームモデル
     * @return 同じ生徒番号を持っている生徒がいたかどうか
     */
    boolean existsSameStudentNumberInTeam(Student student, Team team);

    /**
     * チーム内に存在する生徒のうち、一番大きい値を持つ生徒番号を取得
     * @param team 取得の対象となるチームモデル
     * @return チームの中で一番大きい生徒番号
     *
     * TODO: ITeamRepositoryでの実装の検討
     */
    int fetchMaxStudentNumber(Team team);

    /**
     * studentIdからそれに該当する生徒を取得
     * @param studentId 検索対象のid
     * @return 検索結果
     */
    Optional<Student> findById(String studentId);

    /**
     * teamIdに該当するチームの中の、studentIdに該当する生徒を取得
     * @param studentId 検索対象の生徒id
     * @param teamId 検索対象のチームid
     * @return 検索結果
     *
     * TODO: ITeamRepositoryでの実装の検討
     */
    Optional<Student> findById(String studentId, String teamId);

    /**
     * 生徒idから生徒を取得し、その生徒が所属しているチームを取得
     * @param studentId 取得対象の制度id
     * @return 生徒が参加しているチームのリスト
     *
     * TODO: ITeamRepositoryでの実装の検討
     */
    List<Team> getJoinTeams(String studentId);

    /**
     * studentIdに対応する生徒をDB上から削除
     * @param studentId 削除対象の生徒id
     */
    void delete(String studentId);

    /**
     * 複数の生徒idから、生徒モデルを作成
     * @param studentIds 取得対象の生徒idのリスト
     * @return 生徒idに対応している生徒モデル
     */
    List<Student> fetchStudents(List<String> studentIds);

    /**
     * 生徒モデルに登録されているチームを参加する
     * @param student チームに参加する対象の生徒モデル
     * TODO: モデル構造を含めたメソッドの見直し
     */
    void joinTeam(Student student);
}
