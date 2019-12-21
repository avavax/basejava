$(document).ready(function() {
    const ahHtml = '<div class="form-group"><input type="text" class="form-control" name="ACHIEVEMENT"></div>';
    $('#ADD-ACHIEVEMENT').on('click', () => {
        $('#ADD-ACHIEVEMENT').before(ahHtml);
    })

    const qualHtml = '<div class="form-group"><input type="text" class="form-control" name="QUALIFICATIONS"></div>';
    $('#ADD-QUALIFICATIONS').on('click', () => {
        $('#ADD-QUALIFICATIONS').before(qualHtml);
    })

    const posExp =`
                   <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-8">
                            <label>Специальность</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                            <label>Должностные обязанности</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                    </div>`;
    $('.ADD-EXPERIENCE-POSITION').on('click', (e) => {
        $(e.currentTarget).before(posExp);
    })

    const posEdu =`
                   <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-8">
                            <label>Специальность</label>
                            <input type="text" class="form-control" name="EDUCATION">
                            <input type="hidden" class="form-control" name="EDUCATION" value="">
                        </div>
                    </div>`;
    $('.ADD-EDUCATION-POSITION').on('click', (e) => {
        $(e.currentTarget).before(posEdu);
    })

    const workplace = `
                <div class="row">
                    <div class="col-6">
                        <label>Название организации</label>
                        <input type="text" class="form-control" name="EXPERIENCE">
                    </div>
                    <div class="col-6">
                        <label>Ссылка</label>
                        <input type="text" class="form-control" name="EXPERIENCE">
                    </div>
                </div>
                    <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-8">
                            <label>Специальность</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                            <label>Должностныe обязанности</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                    </div>
                    <button type="button" class="ADD-EXPERIENCE-POSITION" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                <hr>
                <input type="hidden" class="form-control" name="EXPERIENCE" value="end">`;

    $('#ADD-EXPERIENCE').on('click', () => {
        $('#ADD-EXPERIENCE').before(workplace);
    })

    const studyplace = `
                <div class="row">
                    <div class="col-6">
                        <label>Название организации</label>
                        <input type="text" class="form-control" name="EDUCATION">
                    </div>
                    <div class="col-6">
                        <label>Ссылка</label>
                        <input type="text" class="form-control" name="EDUCATION">
                    </div>
                </div>
                    <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" placeholder="YYYY-MM" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-8">
                            <label>Специальность</label>
                            <input type="text" class="form-control" name="EDUCATION">
                            <input type="hidden" class="form-control" name="EDUCATION" value="">
                        </div>
                    </div>
                    <button type="button" class="ADD-EDUCATION-POSITION" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                <hr>
                <input type="hidden" class="form-control" name="EDUCATION" value="end">`;

    $('#ADD-EDUCATION').on('click', () => {
        $('#ADD-EDUCATION').before(studyplace);
    })

});
