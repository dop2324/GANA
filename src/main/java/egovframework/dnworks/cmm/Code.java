package egovframework.dnworks.cmm;

public class Code
{
	public interface ICode
	{
		int getCode();
		String getText();
	}
	
	public enum Error implements ICode
	{
		ErrPnf(1, "존재하지 않는 페이지 요청"),
		ErrSvr(2, "서버 에러 발생"),
		ErrMnu(3, "존재하지 않는 메뉴 요청"),
		ErrPrm(4, "권한없음"),
		ErrPgm(5, "등록되지 않은 프로그램 파일 호출"),
		ErrIll(6, "잘못된 메뉴가 프로그램 호출");
	
		private int code;
		private String text;
	
		Error(int code, String text)
		{
			this.code = code;
			this.text = text;
		}
	
		@Override public int getCode()
		{
			return this.code;
		}
		
		@Override public String getText()
		{
			return this.text;
		}
	}
	
	public enum Prm implements ICode
	{
		HasToken(256,	""),
        PrmPrc(128,		""),
        
		PrmAdm(64,	"관리 권한"),
		PrmDel(32,	"삭제 권한"),
		PrmUpd(16,	"수정 권한"),
		PrmRpl(8,	"답변 권한"),
		PrmIns(4,	"입력 권한"),
		PrmSel(2,	"보기 권한"),
		PrmLst(1,	"조회 권한");
		
		private int code;
		private String text;
	
		Prm(int code, String text)
		{
			this.code = code;
			this.text = text;
		}
	
		@Override public int getCode()
		{
			return this.code;
		}
		
		@Override public String getText()
		{
			return this.text;
		}
	}
	
	public enum MnuType implements ICode
	{
		All(0, "all"),
		Root(1, "root"),
		Menu(2, "menu"),
		MenuPage(3, "menu+page"),
		MenuProgram(4, "menu+program"),
		Page(5, "page"),
		Board(6, "board"),
		Program(7, "program"),
		InnerLink(8, "innerLink"),
		OuterLink(9, "outerLink"),
		ShareBoard(10, "shareBoard");
		
		private int code;
		private String text;
	
		MnuType(int code, String text)
		{
			this.code = code;
			this.text = text;
		}
	
		@Override public int getCode()
		{
			return this.code;
		}
		
		@Override public String getText()
		{
			return this.text;
		}
	}
}
