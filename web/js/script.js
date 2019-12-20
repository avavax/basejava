$(document).ready(function() {
    const ahHtml = '<div class="form-group"><input type="text" class="form-control" name="ACHIEVEMENT"></div>';
    $('#add-achivement').on('click', () => {
        $('#add-achivement').before(ahHtml);
    })

    const qualHtml = '<div class="form-group"><input type="text" class="form-control" name="QUALIFICATIONS"></div>';
    $('#add-qualification').on('click', () => {
        $('#add-qualification').before(qualHtml);
    })

    const posExp =`
                   <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-8">
                            <label>Должность</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                            <label>Должностные обязанности</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                    </div>`;
    $('.add-exp-position').on('click', (e) => {
        $(e.currentTarget).before(posExp);
    })

    const posEdu =`
                   <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-8">
                            <label>Должность</label>
                            <input type="text" class="form-control" name="EDUCATION">
                            <input type="hidden" class="form-control" name="EDUCATION">
                        </div>
                    </div>`;
    $('.add-edu-position').on('click', (e) => {
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
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                        <div class="col-8">
                            <label>Должность</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                            <label>Должностныей обязанности</label>
                            <input type="text" class="form-control" name="EXPERIENCE">
                        </div>
                    </div>
                    <button type="button" class="add-exp-position" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                <hr>
                <input type="hidden" class="form-control" name="EXPERIENCE" value="end">`;

    $('#add-expirience').on('click', () => {
        $('#add-expirience').before(workplace);
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
                            <input type="text" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" class="form-control" name="EDUCATION">
                        </div>
                        <div class="col-8">
                            <label>Специальность</label>
                            <input type="text" class="form-control" name="EDUCATION">
                            <input type="hidden" class="form-control" name="EDUCATION">
                        </div>
                    </div>
                    <button type="button" class="add-exp-position" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                <hr>
                <input type="hidden" class="form-control" name="EDUCATION" value="end">`;

    $('#add-education').on('click', () => {
        $('#add-education').before(studyplace);
    })

});
