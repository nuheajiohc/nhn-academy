-- 1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
select ReleaseYear, Title, RunningTime, Plot from movie where koreanTitle = '퍼스트 맨';

-- 2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
select KoreanTitle, Title from movie where ReleaseYear = 2003;

-- 3. 영화 '글래디에이터'의 작곡가를 고르세요. 
SELECT p.name, p.koreanname
FROM person p
  INNER JOIN appear a ON p.personid = a.personid
  INNER JOIN movie m ON a.movieid = m.movieid
  INNER JOIN role r ON a.roleid = r.roleid
WHERE m.koreantitle = '글래디에이터' AND
	r.rolename = 'composer';

-- 4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(r.rolename = 'dorector') "감독수"
FROM person p
  INNER JOIN appear a ON p.personid = a.personid
  INNER JOIN movie m ON a.movieid = m.movieid
  INNER JOIN role r ON a.roleid = r.roleid
WHERE m.koreantitle = '매트릭스' AND
      r.rolename = 'director';
    
-- 5. 감독이 2명 이상인 영화를 출력하세요
SELECT title
FROM person p
  INNER JOIN appear a ON p.personid = a.personid
  INNER JOIN movie m ON a.movieid = m.movieid
  INNER JOIN role r ON a.roleid = r.roleid
WHERE rolekorname = '감독'
GROUP BY title HAVING count(title)>1;
  
-- 6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
select title, koreantitle
  from appear a
  inner join movie m on a.movieid = m.movieid
  inner join person p on p.personid = a.personid
  inner join awardinvolve ai on ai.appearid = a.appearid
  where winningid = 2 and koreanname = '한스 짐머';
  
-- 7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요
SELECT * 
	FROM movie m
	INNER JOIN appear a1 ON m.movieid = a1.movieid
	INNER JOIN appear a2 ON m.movieid = a2.movieid
	INNER JOIN person p1 ON a1.personid = p1.personid
	INNER JOIN person p2 ON a2.personid = p2.personid
	INNER JOIN role r1 ON a1.roleid = r1.roleid
	INNER JOIN role r2 ON a2.roleid = r2.roleid
WHERE p1.koreanname = '제임스 카메론' AND
	  r1.roleKorName = '감독' AND
	  p2.koreanname = '아놀드 슈워제네거' AND
	  r2.roleName = 'actor';

-- 8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
select distinct m.* from movie m
	inner join appear a on a.movieid = m.movieid
    inner join person p on p.personid = a.personid
    where m.runningtime>=100 and p.koreanname = '레오나르도 디카프리오';

-- 9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
select * from movie
where gradeinkoreaid = (
	select gradeinkoreaid from gradeinkorea
    where gradeinkoreaname = '청소년 관람불가'
    )
order by (BoxOfficeWWGross+BoxOfficeUSGross) desc limit 1;

-- 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
select avg(BoxOfficeWWGross+BoxOfficeUSGross) grossAverage
from movie
where ReleaseYear<1999; 

-- 11. 가장 많은 제작비가 투입된 영화를 고르시오.
select * 
from movie 
order by budget desc 
limit 1;

-- 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
select p.name,p.koreanname
from person p
	inner join appear a on p.personid =a.personid
    inner join role r on r.roleid =a.roleid
    inner join movie m on m.movieid = a.movieid
where r.rolename = 'director'
group by p.personid
order by sum(m.budget) desc
limit 1;

-- 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
select p.name
from person p
	inner join appear a on p.personid = a.personid
    inner join role r on r.roleid = a.roleid
    inner join movie m on m.movieid = a.movieid
where r.rolekorname = '배우'
group by p.personid
order by sum(BoxOfficeWWGross)+sum(BoxOfficeUSGross) desc
limit 1;

-- 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
select (BoxOfficeWWGross+BoxOfficeUSGross) gross
from movie
where budget!=0
order by gross
limit 1;

-- 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
select avg(BoxOfficeUSGross) as averageGross
from movie
where budget <= 50000000;

-- 16. 액션 장르 영화의 평균 수익을 고르세요
select avg(BoxOfficeWWGross+BoxOfficeUSGross) as averageGross
from movie m
	inner join moviegenre mg on m.movieid = mg.movieid
	inner join genre g on g.genreid = mg.genreid
where g.genrename = 'action';

-- 17. 드라마, 전쟁 장르의 영화를 고르세요.
select m.*
from movie m
	inner join moviegenre mg on m.movieid = mg.movieid
    inner join genre g on g.genreid = mg.genreid
where g.genrename in ('war','drama')
group by m.movieid
having count( g.genrename) = 2;

-- 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
select m.title, m.koreanTitle, m.releaseYear 
from movie m
	inner join appear a on a.movieid = m.movieid
    inner join person p on p.personid = a.personid
    inner join role r on r.roleid = a.roleid
where p.koreanName = '톰 행크스' and r.rolename = 'actor'
order by runningTime desc
limit 1;
	
-- 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
select awardList.name, awardList.koreanName
from ( 
select p.name, p.koreanName,count(*) count, rank() over (order by count(*) desc) as ranking
from person p
	inner join appear a on p.personId = a.personId
    inner join role r on r.roleId = a.roleId
    inner join awardinvolve ai on ai.appearId = a.appearId
    inner join sector s on s.sectorId = ai.sectorId
where s.sectorKorName = '남우주연상' and 
	  rolename = 'actor' and
      ai.winningId = (select winningId from winning where winOrNot = 'Winner')
group by p.personId
) awardList where ranking =1;

-- 20. 아카데미상을 가장 많이 수상한 배우를 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
select awardList.name
	from (
	select p.name, count(*), rank() over ( order by count(*) desc) ranking
	from person p
		inner join appear a on a.personId = p.personId
		inner join role r on r.roleId = a.roleId
		inner join awardinvolve ai on ai.appearId = a.appearId
		inner join sector s on s.sectorId = ai.sectorId
	where ai.winningId = (select winningId from winning where winOrNot = 'winner') and
		r.roleKorName = '배우'
	group by p.name
) as awardList
where ranking = 1;

-- 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
select p.name, p.koreanName, count(*) count
from person p
	inner join appear a on p.personId = a.personId
    inner join role r on r.roleId = a.roleId
    inner join awardinvolve ai on ai.appearId = a.appearId
    inner join sector s on s.sectorId = ai.sectorId
where s.sectorKorName = '남우주연상' and 
	  rolename = 'actor' and
      ai.winningId = (select winningId from winning where winOrNot = 'Winner')
group by p.personId
having count>=2;

-- 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
select name, count
from (
	select p.name, count(*) count, rank() over ( order by count(*) desc) ranking
	from person p
		inner join appear a on a.personId = p.personId
		inner join role r on r.roleId = a.roleId
		inner join awardinvolve ai on ai.appearId = a.appearId
		inner join sector s on s.sectorId = ai.sectorId
	where ai.winningId = (select winningId from winning where winOrNot = 'winner') and
		  p.koreanname != '수상자 없음'
	group by p.name
    ) as awardList
where ranking = 1;

-- 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
select title
from (
	select m.title, count(distinct ai.sectorId) count,  rank() over ( order by count(distinct ai.sectorId) desc) ranking
	from movie m
		inner join appear a on a.movieId = m.movieId
		inner join awardinvolve ai on ai.appearId = a.appearId
	group by m.movieId
    ) as movieList
where ranking = 1;

-- 25. 가장 많은 영화에 출연한 여배우를 고르세요.
select p.koreanName, count(m.movieid) count
from person p
	inner join appear a on p.personid = a.personid
    inner join role r on r.roleid = a.roleid
    inner join movie m on m.movieid = a.movieid
where r.rolename = 'actress'
group by p.koreanName
having count
order by count desc
limit 1;

-- 26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
select * 
from movie
order by (BoxOfficeWWGross+BoxOfficeUSGross) desc
limit 10;

-- 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
select *
from movie
where (BoxOfficeWWGross+BoxOfficeUSGross) >= 1000000000 and
	  budget <= 100000000;
      
-- 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
select name
from (
	select p.name, count(*), rank() over (order by count(*) desc) ranking
	from person p
		inner join appear a on a.personId = p.personId
		inner join moviegenre mg on mg.movieId = a.movieId
	where a.roleId = (select roleId from role where roleName = 'director') and
		mg.genreId = (select genreId from genre where genrename = 'war')
	group by p.name
) as directorRanking
where ranking = 1;

-- 29. 드라마에 가장 많이 출연한 사람을 고르세요.
select *
from (
	select p.name, count(*), rank() over (order by count(*) desc) as ranking
	from person p
		inner join appear a on p.personId = a.personId
		inner join moviegenre mg on mg.movieId = a.movieId
	where a.roleId in (select roleId from role where roleKorName = '배우') and
		  mg.genreId = (select genreId from genre where genrename = 'drama')
	group by p.personId
) as DRAMA_ACTOR
where ranking = 1;
      
-- 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
select distinct p1.name, p1.koreanname
FROM person p1
		INNER JOIN appear a1 ON p1.personId = a1.personId
		INNER JOIN moviegenre mg1 ON mg1.movieId = a1.movieId
WHERE a1.roleId in (SELECT roleId FROM role WHERE roleKorName = '배우') AND
	  mg1.genreId in (SELECT genreId FROM genre WHERE genrename = 'drama') AND
      p1.personId NOT IN ( 
			SELECT p2.personId
            FROM person p2
				INNER JOIN appear a2 ON p2.personId = a2.personId
                INNER JOIN moviegenre mg2 ON mg2.movieId = a2.movieId
			WHERE mg2.genreId in (SELECT genreId FROM genre WHERE genrename = 'horror')
	  );

-- 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
SELECT location, count
FROM (
	SELECT location, count(*) count, rank() over ( order by count(*) DESC) ranking
	FROM awardyear
	GROUP BY location
	HAVING count(*)
) as LOCATION_RANK
WHERE ranking = 1;

-- 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
SELECT year first_year, YEAR(CURDATE()) current_year, (YEAR(CURDATE()) - year) elapsed_year
FROM awardyear
ORDER BY year
LIMIT 1;