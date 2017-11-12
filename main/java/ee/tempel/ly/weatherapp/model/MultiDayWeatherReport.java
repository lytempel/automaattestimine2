package ee.tempel.ly.weatherapp.model;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MultiDayWeatherReport implements Iterable<SingleDayWeatherReport>{
    private List<SingleDayWeatherReport> reports;

    public MultiDayWeatherReport(List<SingleDayWeatherReport> reports) {
        this.reports = reports;
    }

    public SingleDayWeatherReport getReport(int i){
        return this.reports.get(i);
    }
    public List<SingleDayWeatherReport> getDay(int i){
        return this.reports.subList(8*i, 8*(i+1));
    }



    @Override
    public Iterator<SingleDayWeatherReport> iterator() {
        return reports.iterator();
    }

    @Override
    public void forEach(Consumer<? super SingleDayWeatherReport> action) {
        reports.forEach(action);
    }

    @Override
    public Spliterator<SingleDayWeatherReport> spliterator() {
        return reports.spliterator();
    }
}
