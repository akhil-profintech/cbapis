import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CBCREProcessDetailComponent } from './cbcre-process-detail.component';

describe('CBCREProcess Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CBCREProcessDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CBCREProcessDetailComponent,
              resolve: { cBCREProcess: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CBCREProcessDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load cBCREProcess on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CBCREProcessDetailComponent);

      // THEN
      expect(instance.cBCREProcess).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
