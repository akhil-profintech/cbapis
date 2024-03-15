import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';
import { IndividualAssessmentService } from 'app/entities/individual-assessment/service/individual-assessment.service';
import { CREObservationsService } from '../service/cre-observations.service';
import { ICREObservations } from '../cre-observations.model';
import { CREObservationsFormService } from './cre-observations-form.service';

import { CREObservationsUpdateComponent } from './cre-observations-update.component';

describe('CREObservations Management Update Component', () => {
  let comp: CREObservationsUpdateComponent;
  let fixture: ComponentFixture<CREObservationsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cREObservationsFormService: CREObservationsFormService;
  let cREObservationsService: CREObservationsService;
  let individualAssessmentService: IndividualAssessmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CREObservationsUpdateComponent],
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
      .overrideTemplate(CREObservationsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CREObservationsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cREObservationsFormService = TestBed.inject(CREObservationsFormService);
    cREObservationsService = TestBed.inject(CREObservationsService);
    individualAssessmentService = TestBed.inject(IndividualAssessmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call IndividualAssessment query and add missing value', () => {
      const cREObservations: ICREObservations = { id: 456 };
      const individualassessment: IIndividualAssessment = { id: 7876 };
      cREObservations.individualassessment = individualassessment;

      const individualAssessmentCollection: IIndividualAssessment[] = [{ id: 19682 }];
      jest.spyOn(individualAssessmentService, 'query').mockReturnValue(of(new HttpResponse({ body: individualAssessmentCollection })));
      const additionalIndividualAssessments = [individualassessment];
      const expectedCollection: IIndividualAssessment[] = [...additionalIndividualAssessments, ...individualAssessmentCollection];
      jest.spyOn(individualAssessmentService, 'addIndividualAssessmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cREObservations });
      comp.ngOnInit();

      expect(individualAssessmentService.query).toHaveBeenCalled();
      expect(individualAssessmentService.addIndividualAssessmentToCollectionIfMissing).toHaveBeenCalledWith(
        individualAssessmentCollection,
        ...additionalIndividualAssessments.map(expect.objectContaining),
      );
      expect(comp.individualAssessmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const cREObservations: ICREObservations = { id: 456 };
      const individualassessment: IIndividualAssessment = { id: 10218 };
      cREObservations.individualassessment = individualassessment;

      activatedRoute.data = of({ cREObservations });
      comp.ngOnInit();

      expect(comp.individualAssessmentsSharedCollection).toContain(individualassessment);
      expect(comp.cREObservations).toEqual(cREObservations);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICREObservations>>();
      const cREObservations = { id: 123 };
      jest.spyOn(cREObservationsFormService, 'getCREObservations').mockReturnValue(cREObservations);
      jest.spyOn(cREObservationsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cREObservations });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cREObservations }));
      saveSubject.complete();

      // THEN
      expect(cREObservationsFormService.getCREObservations).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cREObservationsService.update).toHaveBeenCalledWith(expect.objectContaining(cREObservations));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICREObservations>>();
      const cREObservations = { id: 123 };
      jest.spyOn(cREObservationsFormService, 'getCREObservations').mockReturnValue({ id: null });
      jest.spyOn(cREObservationsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cREObservations: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cREObservations }));
      saveSubject.complete();

      // THEN
      expect(cREObservationsFormService.getCREObservations).toHaveBeenCalled();
      expect(cREObservationsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICREObservations>>();
      const cREObservations = { id: 123 };
      jest.spyOn(cREObservationsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cREObservations });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cREObservationsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareIndividualAssessment', () => {
      it('Should forward to individualAssessmentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(individualAssessmentService, 'compareIndividualAssessment');
        comp.compareIndividualAssessment(entity, entity2);
        expect(individualAssessmentService.compareIndividualAssessment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
