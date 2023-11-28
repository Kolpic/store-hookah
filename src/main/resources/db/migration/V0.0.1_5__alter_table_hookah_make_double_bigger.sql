ALTER TABLE `hookah`
    DROP COLUMN `price`;
ALTER TABLE `hookah`
    ADD COLUMN price DOUBLE(8,2) NOT NULL;