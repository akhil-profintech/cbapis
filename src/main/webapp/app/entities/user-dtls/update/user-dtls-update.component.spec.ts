import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IOrganization } from 'app/entities/organization/organization.model';
import { OrganizationService } from 'app/entities/organization/service/organization.service';
import { UserDtlsService } from '../service/user-dtls.service';
import { IUserDtls } from '../user-dtls.model';
import { UserDtlsFormService } from './user-dtls-form.service';

import { UserDtlsUpdateComponent } from './user-dtls-update.component';

describe('UserDtls Management Update Component', () => {
  let comp: UserDtlsUpdateComponent;
  let fixture: ComponentFixture<UserDtlsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userDtlsFormService: UserDtlsFormService;
  let userDtlsService: UserDtlsService;
  let organizationService: OrganizationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), UserDtlsUpdateComponent],
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
      .overrideTemplate(UserDtlsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserDtlsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userDtlsFormService = TestBed.inject(UserDtlsFormService);
    userDtlsService = TestBed.inject(UserDtlsService);
    organizationService = TestBed.inject(OrganizationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Organization query and add missing value', () => {
      const userDtls: IUserDtls = { id: 456 };
      const organization: IOrganization = { id: 866 };
      userDtls.organization = organization;

      const organizationCollection: IOrganization[] = [{ id: 4982 }];
      jest.spyOn(organizationService, 'query').mockReturnValue(of(new HttpResponse({ body: organizationCollection })));
      const additionalOrganizations = [organization];
      const expectedCollection: IOrganization[] = [...additionalOrganizations, ...organizationCollection];
      jest.spyOn(organizationService, 'addOrganizationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      expect(organizationService.query).toHaveBeenCalled();
      expect(organizationService.addOrganizationToCollectionIfMissing).toHaveBeenCalledWith(
        organizationCollection,
        ...additionalOrganizations.map(expect.objectContaining),
      );
      expect(comp.organizationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const userDtls: IUserDtls = { id: 456 };
      const organization: IOrganization = { id: 8158 };
      userDtls.organization = organization;

      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      expect(comp.organizationsSharedCollection).toContain(organization);
      expect(comp.userDtls).toEqual(userDtls);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserDtls>>();
      const userDtls = { id: 123 };
      jest.spyOn(userDtlsFormService, 'getUserDtls').mockReturnValue(userDtls);
      jest.spyOn(userDtlsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userDtls }));
      saveSubject.complete();

      // THEN
      expect(userDtlsFormService.getUserDtls).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userDtlsService.update).toHaveBeenCalledWith(expect.objectContaining(userDtls));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserDtls>>();
      const userDtls = { id: 123 };
      jest.spyOn(userDtlsFormService, 'getUserDtls').mockReturnValue({ id: null });
      jest.spyOn(userDtlsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userDtls: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userDtls }));
      saveSubject.complete();

      // THEN
      expect(userDtlsFormService.getUserDtls).toHaveBeenCalled();
      expect(userDtlsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserDtls>>();
      const userDtls = { id: 123 };
      jest.spyOn(userDtlsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userDtlsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareOrganization', () => {
      it('Should forward to organizationService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(organizationService, 'compareOrganization');
        comp.compareOrganization(entity, entity2);
        expect(organizationService.compareOrganization).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
