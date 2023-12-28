import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CBCREProcessService } from '../service/cbcre-process.service';
import { ICBCREProcess } from '../cbcre-process.model';
import { CBCREProcessFormService } from './cbcre-process-form.service';

import { CBCREProcessUpdateComponent } from './cbcre-process-update.component';

describe('CBCREProcess Management Update Component', () => {
  let comp: CBCREProcessUpdateComponent;
  let fixture: ComponentFixture<CBCREProcessUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cBCREProcessFormService: CBCREProcessFormService;
  let cBCREProcessService: CBCREProcessService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CBCREProcessUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CBCREProcessUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CBCREProcessUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cBCREProcessFormService = TestBed.inject(CBCREProcessFormService);
    cBCREProcessService = TestBed.inject(CBCREProcessService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const cBCREProcess: ICBCREProcess = { id: 456 };

      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      expect(comp.cBCREProcess).toEqual(cBCREProcess);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICBCREProcess>>();
      const cBCREProcess = { id: 123 };
      jest.spyOn(cBCREProcessFormService, 'getCBCREProcess').mockReturnValue(cBCREProcess);
      jest.spyOn(cBCREProcessService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cBCREProcess }));
      saveSubject.complete();

      // THEN
      expect(cBCREProcessFormService.getCBCREProcess).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cBCREProcessService.update).toHaveBeenCalledWith(expect.objectContaining(cBCREProcess));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICBCREProcess>>();
      const cBCREProcess = { id: 123 };
      jest.spyOn(cBCREProcessFormService, 'getCBCREProcess').mockReturnValue({ id: null });
      jest.spyOn(cBCREProcessService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cBCREProcess: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cBCREProcess }));
      saveSubject.complete();

      // THEN
      expect(cBCREProcessFormService.getCBCREProcess).toHaveBeenCalled();
      expect(cBCREProcessService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICBCREProcess>>();
      const cBCREProcess = { id: 123 };
      jest.spyOn(cBCREProcessService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cBCREProcess });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cBCREProcessService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
