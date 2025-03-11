-- desc country;
-- world db

-- 1. country 테이블에서 유럽(Europe) 대륙에 속하는 모든 국가의 인구수(Population) 의 총합은?
select sum(Population)
from country
where Continent = 'Europe';

-- 2. conutry 테이블에서 대륙(Continent)별 건수, 최대인구수, 최소 Gnp, 최대 Gnp, 평균 기대수명을 구하시오
select Continent, count(*) cnt, max(Population), min(GNP), max(GNP), avg(LifeExpectancy)
from country
group by Continent;