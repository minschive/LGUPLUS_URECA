-- join 연습
-- 1. 도시명 Seoul 속한 국가의 이름, 인구수, GNP 를 조회하시오.
select co.Name, co.Population, co.GNP
from country co, city ci
where co.Code = ci.CountryCode
  and ci.Name = 'Seoul';
  
select co.Name, co.Population, co.GNP
from country co join city ci on co.Code = ci.CountryCode
where ci.Name = 'Seoul';

-- 2. 공식언어의 사용율이 50%(50.0) 가 넘는 국가의 이름, 공식언어, 사용율을 조회하시오.
select co.Name, cl.Language, cl.Percentage
from country co, countrylanguage cl
where co.Code = cl.CountryCode
  and cl.IsOfficial = 'T'
  and cl.Percentage > 50.0;