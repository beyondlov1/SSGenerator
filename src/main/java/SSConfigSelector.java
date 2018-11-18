public interface SSConfigSelector<T> {
    SSConfigEntity selectFrom(ResponseEntity<T> responseEntity);
}
