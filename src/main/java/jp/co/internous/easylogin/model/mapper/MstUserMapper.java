package jp.co.internous.easylogin.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.easylogin.model.domain.MstUser;

@Mapper //データベースにアクセスする役割を持つ
public interface MstUserMapper {
	
	//発行するSQLがSELECT文の場合、＠Selectアノテーションを付与する
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword( String userName, String password );
	// ↑メソッドの引数として受け取った変数をSQL内で使うことができる。#{変数名}と記述
}