SELECT
    A.user_id as 'key'
  , A.user_nm as 'value'
FROM
  m_user A
WHERE
  A.approver_kbn = '1'
ORDER BY
  A.user_id