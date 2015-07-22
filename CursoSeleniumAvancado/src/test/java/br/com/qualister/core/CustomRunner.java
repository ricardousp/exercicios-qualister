package br.com.qualister.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CustomRunner extends BlockJUnit4ClassRunner {

	public CustomRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(final RunNotifier notifier) {
		super.run(notifier);
		WDS.finalizar();
	}

	protected boolean isIgnored(FrameworkMethod child) {
		return child.getAnnotation(Ignore.class) != null;
	}

	protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
		Description description = describeChild(method);
		if (isIgnored(method)) {
			notifier.fireTestIgnored(description);
		} else {
			try {
				runLeafCustom(methodBlock(method), description, notifier);
			} catch (Throwable e) {
				File scrFile = ((TakesScreenshot) WDS.get()).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, new File("evidencias/"+description.toString() + ".png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	protected void runLeafCustom(Statement statement, Description description, RunNotifier notifier) throws Throwable {
		EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
		eachNotifier.fireTestStarted();
		try {
			statement.evaluate();
		} catch (AssumptionViolatedException e) {
			eachNotifier.addFailedAssumption(e);
			throw e;
		} catch (Throwable e) {
			eachNotifier.addFailure(e);
			throw e;
		} finally {
			eachNotifier.fireTestFinished();
		}
	}

}
